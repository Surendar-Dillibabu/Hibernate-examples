package com.jpa.enums.demo.util;

import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class Util {

  public static void nativeQuery(EntityManager em, String queryStr) {
    Query query = em.createNativeQuery(queryStr);
    List ls = query.getResultList();
    for(Object obj : ls) {
      System.out.println(Arrays.toString((Object[]) obj));
    }
  }
}
