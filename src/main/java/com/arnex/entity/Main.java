package com.arnex.entity;

import jakarta.persistence.*;

public class Main {

  public static void main(String[] args) {
    try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default")) {
      try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
        System.out.println("Don't forget to launch Postgres before running this code!");

        // transaction
        EntityTransaction transaction = entityManager.getTransaction();
        try {
          transaction.begin();
          // Perform database operations here
          transaction.commit();
        } catch (Exception e) {
          if (transaction.isActive()) {
            transaction.rollback();
          }
          e.printStackTrace();
        }
      }
    }
  }
}
