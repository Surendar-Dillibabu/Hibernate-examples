package com.hibernate.list.mapping.demo.beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "college_details")
@SequenceGenerator(name = "college_tbl_seq", sequenceName = "college_details_seq", initialValue = 1, allocationSize = 1)
public class College implements Serializable {

  /**
   * 
  */
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "college_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "college_tbl_seq")
  private int collegeId;

  @Column(name = "college_name")
  private String collegeName;

  @ElementCollection
  @CollectionTable(name = "student_details", joinColumns = @JoinColumn(name = "college_id"))
  @Column(name = "student_name")
  private List<Student> students;

  public College() {
  }

  public College(String collegeName) {
    this.collegeName = collegeName;
  }

  public int getCollegeId() {
    return collegeId;
  }

  public void setCollegeId(int collegeId) {
    this.collegeId = collegeId;
  }

  public String getCollegeName() {
    return collegeName;
  }

  public void setCollegeName(String collegeName) {
    this.collegeName = collegeName;
  }

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }

  @Override
  public String toString() {
    return "College [collegeId=" + collegeId + ", collegeName=" + collegeName + ", students=" + students + "]";
  }

}
