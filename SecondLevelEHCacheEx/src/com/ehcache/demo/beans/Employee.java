package com.ehcache.demo.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "EMPLOYEE_TBL")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "employee_region")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "employee_id")
  private long id;

  @Column(name = "employee_name")
  private String name;

  @Column(name = "employee_salary")
  private double salary;

  @OneToOne(mappedBy = "employee")
  @Cascade(value = CascadeType.ALL)
  private Address address;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "Id= " + id + ", Name= " + name + ", Salary= " + salary + ", {Address= " + address + "}";
  }

}
