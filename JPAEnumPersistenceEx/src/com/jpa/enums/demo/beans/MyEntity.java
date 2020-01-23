package com.jpa.enums.demo.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MyEntity {

  @Id
  private long id;

  private MyEnum myEnum;

  public MyEntity() {
  }

  public MyEntity(int id, MyEnum myEnum) {
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
