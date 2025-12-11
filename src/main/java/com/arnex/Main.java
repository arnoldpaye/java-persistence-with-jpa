package com.arnex;

import com.arnex.entity.Employee;
import com.arnex.entity.Salary;
import com.arnex.repository.EmployeeRepositoryImpl;
import jakarta.persistence.*;

import java.util.List;

public class Main {

  public static void main(String[] args) {
    try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default")) {
      try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
        EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl(entityManager);
        // create employee
        Employee employee = new Employee();
        employee.setFirstName("Mary");
        employee.setLastName("Doe");
        employee.setYearsExperience(20);

        employee.setSalaries(generateSalaries());

        // save employee
        employeeRepository.save(employee);
      }
    }
  }

  private static List<Salary> generateSalaries() {
    Salary currentSalary = new Salary(2000.00, true);
    Salary historicalSalary1 = new Salary(1500.00, false);
    Salary historicalSalary2 = new Salary(900.00, false);
    return  List.of(currentSalary, historicalSalary1, historicalSalary2);
  }
}
