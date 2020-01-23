package com.jpa.enums.demo.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.jpa.enums.demo.beans.MyEntity2;
import com.jpa.enums.demo.beans.MyEnum;
import com.jpa.enums.demo.util.Util;

/**
 * Enum default store the Ordinal value in DB if we don't provide
 * the @Enumerated as STRING.
 * 
 * In this below sample MyEntity2 class is annotated
 * with @Enumerated(EnumType.STRING) so it will store the enum name in the table
 * 
 * @author gbs04543
 *
 */
public class ExampleEnumeratedAnnotation {

  private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("enum_ex_unit");

  public static void main(String[] args) {
    persistEntity();
    loadEntity();
  }

  public static void persistEntity() {
    MyEntity2 entity = new MyEntity2(1, MyEnum.CONSTANT_C);
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.persist(entity);
    em.getTransaction().commit();
    em.close();
    System.out.println("MyEntity2 persisted: " + entity);
  }

  public static void loadEntity() {
    EntityManager em = emf.createEntityManager();
    Util.nativeQuery(em, "select * from MyEntity2");
    MyEntity2 myEntity2 = em.find(MyEntity2.class, 1L);
    System.out.printf("Persisted entity details %s%n", myEntity2);
    em.close();
  }
}
