package com.named.stored.proc.demo.client;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.StoredProcedureQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.named.stored.proc.demo.beans.Employee;
import com.named.stored.proc.demo.util.HibernateUtil;

public class NamedStoredProcedureCall {

  public static void main(String[] args) {
    NamedStoredProcedureCall storedProcedureCall = new NamedStoredProcedureCall();

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

    StoredProcedureQuery procedureQuery = session.createNamedStoredProcedureQuery(Employee.UPDATE_EMP_DETAILS_PRC);
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

    StoredProcedureQuery procedureQuery = session.createNamedStoredProcedureQuery(Employee.GET_EMP_DETAILS_PRC);
    procedureQuery.execute();
    @SuppressWarnings("unchecked")
    List<Employee> resultList = procedureQuery.getResultList();

    resultList.forEach(System.out::println);

    session.close();
  }
}
