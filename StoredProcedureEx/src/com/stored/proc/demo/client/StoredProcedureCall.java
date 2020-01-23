package com.stored.proc.demo.client;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.stored.proc.demo.beans.Employee;
import com.stored.proc.demo.util.HibernateUtil;

public class StoredProcedureCall {

  public static void main(String[] args) {
    StoredProcedureCall storedProcedureCall = new StoredProcedureCall();

    System.out.println("Saving employee details");
    SaveEmployeeData obj = new SaveEmployeeData();
    obj.save();

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    System.out.println("Getting employee details before updating");
    storedProcedureCall.getEmployeeDetailsPrcCall(sessionFactory);

    System.out.println("Updating employee details through procedure call");
    storedProcedureCall.updateEmployeeDetails(sessionFactory);

    System.out.println("Getting employee details after updating");
    storedProcedureCall.getEmployeeDetailsPrcCall(sessionFactory);

    sessionFactory.close();
  }

  public void updateEmployeeDetails(SessionFactory sessionFactory) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();

    StoredProcedureQuery procedureQuery = session.createStoredProcedureQuery("HIBERNATE_TRAINING_PKG.UPD_EMPLOYEE_DETAILS");
    procedureQuery.registerStoredProcedureParameter("IN_EMPLOYEE_ID", Integer.class, ParameterMode.IN);
    procedureQuery.registerStoredProcedureParameter("IN_EMPLOYEE_NAME", String.class, ParameterMode.IN);
    procedureQuery.registerStoredProcedureParameter("IN_JOINING_DATE", LocalDate.class, ParameterMode.IN);
    procedureQuery.registerStoredProcedureParameter("IN_SALARY", Long.class, ParameterMode.IN);
    procedureQuery.registerStoredProcedureParameter("OU_RESULT", String.class, ParameterMode.OUT);
    procedureQuery.setParameter("IN_EMPLOYEE_ID", 1);
    procedureQuery.setParameter("IN_EMPLOYEE_NAME", "Surendar.D");
    procedureQuery.setParameter("IN_JOINING_DATE", LocalDate.of(2017, 01, 20));
    procedureQuery.setParameter("IN_SALARY", 180000L);
    procedureQuery.execute();

    String result = (String) procedureQuery.getOutputParameterValue("OU_RESULT");
    System.out.println("Update employee details procedure call result :" + result);

    session.getTransaction().commit();
    session.close();
  }

  public void getEmployeeDetailsPrcCall(SessionFactory sessionFactory) {
    Session session = sessionFactory.openSession();

    StoredProcedureQuery procedureQuery = session.createStoredProcedureQuery("HIBERNATE_TRAINING_PKG.GET_EMPLOYEE_DETAILS", Employee.class);
    procedureQuery.registerStoredProcedureParameter("EMP_CURSOR", void.class, ParameterMode.REF_CURSOR);
    procedureQuery.execute();
    @SuppressWarnings("unchecked")
    List<Employee> resultList = procedureQuery.getResultList();

    resultList.forEach(System.out::println);

    session.close();
  }
}
