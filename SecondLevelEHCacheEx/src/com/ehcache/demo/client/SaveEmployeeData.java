package com.ehcache.demo.client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.ehcache.demo.beans.Address;
import com.ehcache.demo.beans.Employee;
import com.ehcache.demo.util.HibernateUtil;

public class SaveEmployeeData {

  public void save() {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    Session sess = sessionFactory.openSession();
    Transaction trans = sess.beginTransaction();

    Employee emp1 = getTestEmployee(1);
    sess.save(emp1);

    Employee emp2 = getTestEmployee(2);
    sess.save(emp2);

    Employee emp3 = getTestEmployee(3);
    sess.save(emp3);

    trans.commit();
    sess.clear();
    sess.close();
  }

  public static Employee getTestEmployee(int id) {
    Employee emp = new Employee();
    Address add = new Address();
    emp.setName("Test Emp-" + id);
    emp.setSalary(1000);
    add.setAddressLine1("Test address-" + id);
    add.setCity("Test City-" + id);
    add.setZipcode("12121-" + id);
    emp.setAddress(add);
    add.setEmployee(emp);
    return emp;
  }
}
