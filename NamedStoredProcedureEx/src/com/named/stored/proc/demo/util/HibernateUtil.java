package com.named.stored.proc.demo.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

  public static SessionFactory sessionFactory = null;

  public static SessionFactory buildSessionFactory() {
    StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
        .configure("/META-INF/hibernate.cfg.xml").build();
    return new MetadataSources(standardRegistry).buildMetadata().buildSessionFactory();
  }

  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null)
      sessionFactory = buildSessionFactory();

    return sessionFactory;
  }
}
