package com.jpa.enums.demo.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.jpa.enums.demo.beans.MyEntity;
import com.jpa.enums.demo.beans.MyEnum;
import com.jpa.enums.demo.util.Util;

/**
 * Enum default store the Ordinal value in DB if we don't provide
 * the @Enumerated as STRING.
 * 
 * Here in this below example if you check the MyEntity table in DB it will have
 * a enum ordinal value
 * 
 * @author gbs04543
 *
 */
public class ExampleDefaultEnumPersistence {

  private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("enum_ex_unit");

  public static void main(String[] args) {
    persistEntity();
    loadEntity();
  }

  public static void persistEntity() {
    MyEntity entity = new MyEntity(1, MyEnum.CONSTANT_C);
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.persist(entity);
    em.getTransaction().commit();
    em.close();
    System.out.println("MyEntity persisted: " + entity);
  }

  public static void loadEntity() {
    EntityManager em = emf.createEntityManager();
    Util.nativeQuery(em, "select * from MyEntity");
    MyEntity myEntity = em.find(MyEntity.class, 1L);
    System.out.printf("Persisted entity details %s%n", myEntity);
    em.close();
  }
}
