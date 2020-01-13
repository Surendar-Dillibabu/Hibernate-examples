package com.hibernate.list.mapping.demo.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.hibernate.list.mapping.demo.beans.College;
import com.hibernate.list.mapping.demo.beans.Student;

public class Application {

  public static void main(String[] args) {
    EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory("list_mapping_unit");
    EntityManager entityManager = entityFactory.createEntityManager();

    entityManager.getTransaction().begin();

    College c1 = new College("Thangavelu Enginnering College");
    College c2 = new College("Anna university");
    entityManager.persist(c1);
    entityManager.persist(c2);

    Student s1 = new Student("Surendar", c1.getCollegeId());
    entityManager.persist(s1);

    Student s2 = new Student("Gokul", c2.getCollegeId());
    entityManager.persist(s2);

    Student s3 = new Student("Ramkumar", c1.getCollegeId());
    entityManager.persist(s3);

    Student s4 = new Student("Tamil", c1.getCollegeId());
    entityManager.persist(s4);

    entityManager.getTransaction().commit();
    entityManager.refresh(c1);
    entityManager.refresh(c2);

    System.out.println("Thangavelu enginnering college details");
    College c3 = entityManager.find(College.class, c1.getCollegeId());
    System.out.println(c3);

    System.out.println("Anna university college details");
    College c4 = entityManager.find(College.class, c2.getCollegeId());
    System.out.println(c4);

    entityManager.close();
    entityFactory.close();
  }
}
