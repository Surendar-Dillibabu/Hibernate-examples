package com.manytomany.demo.beans;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "question_tbl")
public class Question {

  @Id
  @GeneratedValue
  @Column(name = "question_id")
  private int questionId;

  @Column(name = "question_name")
  private String questionName;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "question_answer_tbl", joinColumns = @JoinColumn(name = "question_id"), inverseJoinColumns = @JoinColumn(name = "answer_id"))
  private List<Answer> answers;

  public int getQuestionId() {
    return questionId;
  }

  public void setQuestionId(int questionId) {
    this.questionId = questionId;
  }

  public String getQuestionName() {
    return questionName;
  }

  public void setQuestionName(String questionName) {
    this.questionName = questionName;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }

  @Override
  public String toString() {
    return "Question [questionId=" + questionId + ", questionName=" + questionName + ", answers=" + answers + "]";
  }

}
