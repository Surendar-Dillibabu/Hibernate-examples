package com.tbl.per.cls.inheritance.demo.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "employee_tbl")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Employee {

  @Id
  @GeneratedValue
  @Column(name = "employee_id")
  private int employeeId;

  @Column(name = "employee_name")
  private String employeeName;

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

  @Override
  public String toString() {
    return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + "]";
  }

}
