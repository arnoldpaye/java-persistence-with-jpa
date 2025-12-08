package com.arnex.entity;

import com.arnex.repository.EmployeeRepositoryImpl;
import jakarta.persistence.*;

public class Main {

  public static void main(String[] args) {
    try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default")) {
      try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
        EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl(entityManager);
        // create employees
        Employee employee1 = new Employee();
        employee1.setfName("Mary");
        employee1.setlName("Doe");
        employee1.setYearsExperience(20);

        Employee employee2 = new Employee();
        employee2.setfName("James");
        employee2.setlName("Doe");
        employee2.setYearsExperience(5);

        // save employees
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
      }
    }
  }
}
