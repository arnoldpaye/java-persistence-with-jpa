package com.arnex;

import com.arnex.entity.Employee;
import com.arnex.repository.EmployeeRepositoryImpl;
import jakarta.persistence.*;

import java.util.Optional;

public class Main {

  public static void main(String[] args) {
    try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default")) {
      try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
        EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl(entityManager);
        // create employee
        Employee employee = new Employee();
        employee.setfName("Mary");
        employee.setlName("Doe");
        employee.setYearsExperience(20);

        // save employee
        Optional<Employee> savedEmployee = employeeRepository.save(employee);
        System.out.println(savedEmployee);
      }
    }
  }
}
