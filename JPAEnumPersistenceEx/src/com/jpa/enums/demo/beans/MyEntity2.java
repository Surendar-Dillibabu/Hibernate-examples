package com.jpa.enums.demo.beans;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class MyEntity2 {

  @Id
  private long id;

  @Enumerated(EnumType.STRING)
  private MyEnum myEnum;

  public MyEntity2() {
  }

  public MyEntity2(int id, MyEnum myEnum) {
    this.id = id;
    this.myEnum = myEnum;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public MyEnum getMyEnum() {
    return myEnum;
  }

  public void setMyEnum(MyEnum myEnum) {
    this.myEnum = myEnum;
  }

  @Override
  public String toString() {
    return "MyEntity [id=" + id + ", myEnum=" + myEnum + "]";
  }
}
