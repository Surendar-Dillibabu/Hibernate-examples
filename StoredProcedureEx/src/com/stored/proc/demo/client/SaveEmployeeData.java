package com.stored.proc.demo.client;

import java.time.LocalDate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.stored.proc.demo.beans.Employee;
import com.stored.proc.demo.util.HibernateUtil;

public class SaveEmployeeData {

  public void save() {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();

    session.beginTransaction();

    session.save(new Employee("Surendar", 10000L, LocalDate.of(2017, 03, 20)));
    session.save(new Employee("Vivek", 9000L, LocalDate.of(2017, 05, 05)));
    session.save(new Employee("Sushmanth", 9500L, LocalDate.of(2017, 05, 25)));

    session.getTransaction().commit();
    System.out.println("Employee details saved successfully");

    session.close();
  }

}
