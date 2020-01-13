package com.hibernate.demo.client;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.hibernate.demo.beans.Department;
import com.hibernate.demo.beans.Employee;

public class Application {

  public static void main(String[] args) {
    EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory("employee_unit");
    EntityManager entityManager = entityFactory.createEntityManager();

    // create new transaction
    entityManager.getTransaction().begin();

    // inserting department details
    Department hrDept = new Department("HR");
    entityManager.persist(hrDept);

    entityManager.persist(new Department("Admin"));
    entityManager.persist(new Department("Networking"));

    Department itDept = new Department("HR");
    entityManager.persist(itDept);

    entityManager.persist(new Department("Customer care"));
    entityManager.persist(new Department("Analytics"));

    // inserting employee details
    System.out.println("HR department id :" + hrDept.getDeptId());
    Employee employee = new Employee("Hema", 10000L, hrDept.getDeptId(), new Date());
    entityManager.persist(employee);
    System.out.println("Generated employee id :" + employee.getEmployeeId());

    System.out.println("IT department id :" + itDept.getDeptId());
    Employee employee1 = new Employee("Yuvaraj", 10000L, itDept.getDeptId(), new Date());
    entityManager.persist(employee1);
    System.out.println("Generated employee id :" + employee1.getEmployeeId());

    // Getting employee details using entity manager find method
    Employee empDetails = entityManager.find(Employee.class, employee.getEmployeeId());
    System.out.println(empDetails);

    // updating the employee details.
    empDetails.setEmployeeName("HR Hema");

    // deleting the employee details
    Employee empDetails1 = entityManager.find(Employee.class, employee1.getEmployeeId());
    entityManager.remove(empDetails1);

    // committing the transaction
    entityManager.getTransaction().commit();

    entityManager.close();
    entityFactory.close();
  }
}
