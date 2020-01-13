package com.hibernate.demo.beans;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "employee_details")
@SequenceGenerator(name = "employee_tbl_seq", sequenceName = "employee_details_seq", initialValue = 1, allocationSize = 1)
public class Employee {

  @Id
  @Column(name = "employee_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_tbl_seq")
  private int employeeId;

  @Column(name = "employee_name")
  private String employeeName;

  @Column(name = "salary")
  private Long salary;

  @Column(name = "dept_id")
  private int deptId;

  @Column(name = "joining_date")
  private Date joiningDate;

  public Employee() {
  }

  public Employee(String employeeName, Long salary, int deptId, Date joiningDate) {
    this.employeeName = employeeName;
    this.salary = salary;
    this.deptId = deptId;
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

  public int getDeptId() {
    return deptId;
  }

  public void setDeptId(int deptId) {
    this.deptId = deptId;
  }

  public Date getJoiningDate() {
    return joiningDate;
  }

  public void setJoiningDate(Date joiningDate) {
    this.joiningDate = joiningDate;
  }

  @Override
  public String toString() {
    return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", salary=" + salary + ", deptId="
        + deptId + ", joiningDate=" + joiningDate + "]";
  }

}
