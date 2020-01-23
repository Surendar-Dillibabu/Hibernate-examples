package com.manytomany.demo.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "answer_tbl")
public class Answer {

  @Id
  @GeneratedValue
  @Column(name = "answer_id")
  private int answerId;

  @Column(name = "answer_name")
  private String answerName;

  @Column(name = "posted_by")
  private String postedBy;

  public int getAnswerId() {
    return answerId;
  }

  public void setAnswerId(int answerId) {
    this.answerId = answerId;
  }

  public String getAnswerName() {
    return answerName;
  }

  public void setAnswerName(String answerName) {
    this.answerName = answerName;
  }

  public String getPostedBy() {
    return postedBy;
  }

  public void setPostedBy(String postedBy) {
    this.postedBy = postedBy;
  }

  @Override
  public String toString() {
    return "Answer [answerId=" + answerId + ", answerName=" + answerName + ", postedBy=" + postedBy + "]";
  }

}
