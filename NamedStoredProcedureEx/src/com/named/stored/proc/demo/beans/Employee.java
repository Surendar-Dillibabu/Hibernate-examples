package com.named.stored.proc.demo.beans;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@Table(name = "employee_tbl")
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(name = Employee.UPDATE_EMP_DETAILS_PRC, procedureName = "HIBERNATE_TRAINING_PKG.UPD_EMPLOYEE_DETAILS", parameters = {
        @StoredProcedureParameter(name = "IN_EMPLOYEE_ID", type = Integer.class, mode = ParameterMode.IN),
        @StoredProcedureParameter(name = "IN_EMPLOYEE_NAME", type = String.class, mode = ParameterMode.IN),
        @StoredProcedureParameter(name = "IN_JOINING_DATE", type = LocalDate.class, mode = ParameterMode.IN),
        @StoredProcedureParameter(name = "IN_SALARY", type = Long.class, mode = ParameterMode.IN),
        @StoredProcedureParameter(name = "OU_RESULT", type = String.class, mode = ParameterMode.OUT), }),
    @NamedStoredProcedureQuery(name = Employee.GET_EMP_DETAILS_PRC, procedureName = "HIBERNATE_TRAINING_PKG.GET_EMPLOYEE_DETAILS", resultClasses = {
        Employee.class }, parameters = {
            @StoredProcedureParameter(name = "EMP_CURSOR", type = void.class, mode = ParameterMode.REF_CURSOR) }) })
public class Employee {

  public static final String UPDATE_EMP_DETAILS_PRC = "UPDATE_EMP_DETAILS_PRC";
  public static final String GET_EMP_DETAILS_PRC = "GET_EMP_DETAILS_PRC";

  @Id
  @GeneratedValue
  @Column(name = "employee_id")
  private int employeeId;

  @Column(name = "employee_name")
  private String employeeName;

  @Column(name = "salary")
  private Long salary;

  @Column(name = "joining_date")
  private LocalDate joiningDate;

  public Employee() {
  }

  public Employee(String employeeName, Long salary, LocalDate joiningDate) {
    this.employeeName = employeeName;
    this.salary = salary;
    this.joiningDate = joiningDate;
  }

  public int getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  public Long getSalary() {
    return salary;
  }

  public void setSalary(Long salary) {
    this.salary = salary;
  }

  public LocalDate getJoiningDate() {
    return joiningDate;
  }

  public void setJoiningDate(LocalDate joiningDate) {
    this.joiningDate = joiningDate;
  }

  @Override
  public String toString() {
    return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", salary=" + salary
        + ", joiningDate=" + joiningDate + "]";
  }

}
