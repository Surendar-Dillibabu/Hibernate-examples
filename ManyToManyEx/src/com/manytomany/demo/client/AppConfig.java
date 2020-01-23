package com.manytomany.demo.client;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.manytomany.demo.beans.Answer;
import com.manytomany.demo.beans.Question;

public class AppConfig {

  public static void main(String[] args) {
    SessionFactory sessionFactory = new Configuration().configure("/META-INF/hibernate.cfg.xml").buildSessionFactory();
    try {
      persist(sessionFactory);
      load(sessionFactory);
    } finally {
      sessionFactory.close();
    }
  }

  private static void load(SessionFactory sessionFactory) {
    System.out.println("-- loading questions --");
    Session session = sessionFactory.openSession();
    
    @SuppressWarnings("unchecked")
    List<Question> question = session.createQuery("FROM Question").list();
    question.forEach((x) -> System.out.printf("- %s%n", x));
    
    session.close();
  }

  private static void persist(SessionFactory sessionFactory) {
    System.out.println("-- Persisting questions --");
    Session session = sessionFactory.openSession();
    session.beginTransaction();

    Answer an1 = new Answer();
    an1.setAnswerName("Java is a programming language");
    an1.setPostedBy("Ravi Malik");

    Answer an2 = new Answer();
    an2.setAnswerName("Java is a platform");
    an2.setPostedBy("Sudhir Kumar");

    Question q1 = new Question();
    q1.setQuestionName("What is Java?");
    ArrayList<Answer> l1 = new ArrayList<Answer>();
    l1.add(an1);
    l1.add(an2);
    q1.setAnswers(l1);

    Answer ans3 = new Answer();
    ans3.setAnswerName("Servlet is an Interface");
    ans3.setPostedBy("Jai Kumar");

    Answer ans4 = new Answer();
    ans4.setAnswerName("Servlet is an API");
    ans4.setPostedBy("Arun");

    Question q2 = new Question();
    q2.setQuestionName("What is Servlet?");
    ArrayList<Answer> l2 = new ArrayList<Answer>();
    l2.add(ans3);
    l2.add(ans4);
    q2.setAnswers(l2);

    session.persist(q1);
    session.persist(q2);

    session.getTransaction().commit();
  }
}
