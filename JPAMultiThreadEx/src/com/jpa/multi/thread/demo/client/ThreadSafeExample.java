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

public class ThreadSafeExample {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("stocks_unit");
    ExecutorService service = Executors.newFixedThreadPool(2);
    List<Future<String>> futures = new ArrayList<>();

    Future<String> f1 = service.submit(() -> {
      return runTask(emf, 1, "Reliance");
    });

    Future<String> f2 = service.submit(() -> {
      return runTask(emf, 2, "Airtel");
    });

    futures.add(f1);
    futures.add(f2);

    for (Future<String> fut : futures) {
      System.out.println(fut.get());
    }

    nativeQuery(emf, "Select * from stock_details");

    service.shutdown();
    emf.close();
  }

  public static String runTask(EntityManagerFactory emf, int stockId, String stockName) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.persist(createStockEntity(stockId, stockName));
    em.getTransaction().commit();
    em.close();
    return "Persisted id :" + stockId;
  }

  public static StockDetails createStockEntity(int stockId, String stockName) {
    StockDetails stockDetails = new StockDetails();
    stockDetails.setStockId(stockId);
    stockDetails.setStockName(stockName);
    return stockDetails;
  }

  public static void nativeQuery(EntityManagerFactory emf, String queryStr) {
    EntityManager em = emf.createEntityManager();
    Query query = em.createNativeQuery(queryStr);
    List ls = query.getResultList();
    for (Object obj : ls) {
      System.out.println(Arrays.toString((Object[]) obj));
    }
    em.close();
  }
}
