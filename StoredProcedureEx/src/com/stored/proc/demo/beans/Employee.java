package com.stored.proc.demo.beans;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_tbl")
public class Employee {

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
