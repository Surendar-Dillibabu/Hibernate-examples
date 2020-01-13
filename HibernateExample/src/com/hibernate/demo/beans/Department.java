package com.hibernate.demo.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "dept_details")
@SequenceGenerator(name = "dept_tbl_seq", sequenceName = "dept_details_seq", initialValue = 1, allocationSize = 1)
public class Department {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dept_tbl_seq")
  @Column(name = "dept_id")
  private int deptId;

  @Column(name = "dept_name")
  private String departmentName;

  public Department() {
  }

  public Department(String departmentName) {
    this.departmentName = departmentName;
  }

  public int getDeptId() {
    return deptId;
  }

  public void setDeptId(int deptId) {
    this.deptId = deptId;
  }

  public String getDepartmentName() {
    return departmentName;
  }

  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }

  @Override
  public String toString() {
    return "Department [deptId=" + deptId + ", departmentName=" + departmentName + "]";
  }

}
