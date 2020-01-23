package com.ehcache.demo.client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;
import com.ehcache.demo.beans.Address;
import com.ehcache.demo.beans.Employee;
import com.ehcache.demo.util.HibernateUtil;

public class HibernateEHCacheMain {

  public static void main(String[] args) {

    System.out.println("Temp Dir:" + System.getProperty("java.io.tmpdir"));
    SaveEmployeeData obj = new SaveEmployeeData();
    obj.save();

    // Initialize Sessions
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Statistics stats = sessionFactory.getStatistics();
    System.out.println("Stats enabled=" + stats.isStatisticsEnabled());
    stats.setStatisticsEnabled(true);
    System.out.println("Stats enabled=" + stats.isStatisticsEnabled());

    Session session = sessionFactory.openSession();
    Session otherSession = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    Transaction otherTransaction = otherSession.beginTransaction();

    printStats(stats, 0);

    Employee emp = (Employee) session.load(Employee.class, 1L);
    printData(emp, stats, 1);

    emp = (Employee) session.load(Employee.class, 1L);
    printData(emp, stats, 2);

    // clear first level cache, so that second level cache is used
    session.evict(emp);
    emp = (Employee) session.load(Employee.class, 1L);
    printData(emp, stats, 3);

    emp = (Employee) session.load(Employee.class, 3L);
    printData(emp, stats, 4);

    emp = (Employee) otherSession.load(Employee.class, 1L);
    printData(emp, stats, 5);

    // Release resources
    transaction.commit();
    otherTransaction.commit();
    session.close();
    otherSession.close();
    sessionFactory.close();
  }

  public static Employee getTestEmployee(int id) {
    Employee emp = new Employee();
    Address add = new Address();
    emp.setName("Test Emp-" + id);
    emp.setSalary(1000);
    add.setAddressLine1("Test address1-" + id);
    add.setCity("Test City");
    add.setZipcode("12121");
    emp.setAddress(add);
    add.setEmployee(emp);
    return emp;
  }

  private static void printStats(Statistics stats, int i) {
    System.out.println("***** " + i + " *****");
    System.out.println("Fetch Count=" + stats.getEntityFetchCount());
    System.out.println("Second Level Hit Count=" + stats.getSecondLevelCacheHitCount());
    System.out.println("Second Level Miss Count=" + stats.getSecondLevelCacheMissCount());
    System.out.println("Second Level Put Count=" + stats.getSecondLevelCachePutCount());
  }

  private static void printData(Employee emp, Statistics stats, int count) {
    System.out.println(count + ":: Name=" + emp.getName() + ", Zipcode=" + emp.getAddress().getZipcode());
    printStats(stats, count);
  }
}
