package com.arnex.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class Main {

  @PersistenceContext
  EntityManager entityManager;
  public static void main(String[] args) {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    System.out.println("Don't forget to launch Postgres before running this code!");

    entityManager.close();
    entityManagerFactory.close();
  }
}
