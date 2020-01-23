package com.jpa.multi.thread.demo.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.jpa.multi.thread.demo.beans.StockDetails;

/**
 * When you execute the below code we will get below exception since the same
 * EntityManager instance is used in both threads. Sometimes it won't throw the
 * below error
 * 
 * Exception in thread "main" java.util.concurrent.ExecutionException:
 * javax.persistence.EntityExistsException: A different object with the same
 * identifier value was already associated with the session :
 * [com.jpa.multi.thread.demo.beans.StockDetails#1]
 * 
 * @author gbs04543
 *
 */
public class NotThreadSafeExample {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("stocks_unit");
    EntityManager em = emf.createEntityManager();
    ExecutorService service = Executors.newFixedThreadPool(2);
    List<Future<String>> futures = new ArrayList<>();

    Future<String> f1 = service.submit(() -> {
      return runTask(em, 1, "Reliance");
    });

    Future<String> f2 = service.submit(() -> {
      return runTask(em, 2, "Airtel");
    });

    futures.add(f1);
    futures.add(f2);

    for (Future<String> fut : futures) {
      System.out.println(fut.get());
    }

    nativeQuery(em, "select * from stock_details");

    service.shutdown();
    em.close();
    emf.close();
  }

  public static String runTask(EntityManager em, int stockId, String stockName) {
    em.getTransaction().begin();
    em.persist(createStockEntity(stockId, stockName));
    em.getTransaction().commit();
    return "Persisted id :" + stockId;
  }

  public static StockDetails createStockEntity(int stockId, String stockName) {
    StockDetails stockDetails = new StockDetails();
    stockDetails.setStockId(stockId);
    stockDetails.setStockName(stockName);
    return stockDetails;
  }

  public static void nativeQuery(EntityManager em, String queryStr) {
    Query query = em.createNativeQuery(queryStr);
    List ls = query.getResultList();
    for (Object obj : ls) {
      System.out.println(Arrays.toString((Object[]) obj));
    }
  }
}
