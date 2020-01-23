package com.joined.inheritance.demo.client;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.joined.inheritance.demo.beans.ContractEmployee;
import com.joined.inheritance.demo.beans.Employee;
import com.joined.inheritance.demo.beans.RegularEmployee;

public class AppConfig {

  public static void main(String[] args) {
    SessionFactory sessionFactory = new Configuration().configure("/META-INF/hibernate.cfg.xml").buildSessionFactory();
    try {
      persist(sessionFactory);
      load(sessionFactory);
      loadContractEmployees(sessionFactory);
    } finally {
      sessionFactory.close();
    }
  }

  private static void load(SessionFactory sessionFactory) {
    System.out.println("-- loading employees --");
    Session session = sessionFactory.openSession();

    @SuppressWarnings("unchecked")
    List<Employee> employeeList = session.createQuery("FROM Employee").list();
    employeeList.forEach((x) -> System.out.printf("- %s%n", x));

    session.close();
  }

  private static void loadContractEmployees(SessionFactory sessionFactory) {
    System.out.println("-- loading contact employees --");
    Session session = sessionFactory.openSession();

    @SuppressWarnings("unchecked")
    List<Employee> employeeList = session.createQuery("FROM ContractEmployee").list();
    employeeList.forEach((x) -> System.out.printf("- %s%n", x));

    session.close();
  }

  private static void persist(SessionFactory sessionFactory) {
    System.out.println("-- Persisting employees --");
    Session session = sessionFactory.openSession();
    session.beginTransaction();

    Employee e1 = new Employee();
    e1.setEmployeeName("Gaurav Chawla");

    RegularEmployee e2 = new RegularEmployee();
    e2.setEmployeeName("Vivek Kumar");
    e2.setSalary(50000);
    e2.setBonus(5);

    ContractEmployee e3 = new ContractEmployee();
    e3.setEmployeeName("Arjun Kumar");
    e3.setPay_per_hour(1000);
    e3.setContract_duration("15 hours");

    ContractEmployee e4 = new ContractEmployee();
    e4.setEmployeeName("Raja Ayyappan");
    e4.setPay_per_hour(500);
    e4.setContract_duration("10 hours");

    session.persist(e1);
    session.persist(e2);
    session.persist(e3);
    session.persist(e4);

    session.getTransaction().commit();
  }
}
