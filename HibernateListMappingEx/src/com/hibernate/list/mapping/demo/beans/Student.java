package com.hibernate.list.mapping.demo.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "student_details")
@SequenceGenerator(name = "student_tbl_seq", sequenceName = "student_details_seq", initialValue = 1, allocationSize = 1)
public class Student implements Serializable {

  /**
   * 
  */
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "student_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_tbl_seq")
  private int studentId;

  @Column(name = "student_name")
  private String studentName;

  @Column(name = "college_id")
  private int collegeId;

  public Student() {
  }

  public Student(String studentName, int collegeId) {
    this.studentName = studentName;
    this.collegeId = collegeId;
  }

  public int getStudentId() {
    return studentId;
  }

  public void setStudentId(int studentId) {
    this.studentId = studentId;
  }

  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public int getCollegeId() {
    return collegeId;
  }

  public void setCollegeId(int collegeId) {
    this.collegeId = collegeId;
  }

  @Override
  public String toString() {
    return "Student [studentId=" + studentId + ", studentName=" + studentName + ", collegeId=" + collegeId + "]";
  }

}
