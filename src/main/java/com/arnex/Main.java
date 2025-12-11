package com.arnex;

import com.arnex.entity.Company;
import com.arnex.entity.Employee;
import com.arnex.entity.Salary;
import com.arnex.repository.EmployeeRepositoryImpl;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default")) {
      try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
        EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl(entityManager);
        // create employees
        Employee emp1 = new Employee();
        emp1.setFirstName("Mary");
        emp1.setLastName("Doe");
        emp1.setYearsExperience(20);

        Employee emp2 = new Employee();
        emp2.setFirstName("John");
        emp2.setLastName("Doe");
        emp2.setYearsExperience(5);

        emp1.setCompanies(generateCompanies());
        emp2.setCompanies(generateCompanies());

        emp1.setSalaries(generateSalaries());
        emp2.setSalaries(generateSalaries());

        // save employee
        employeeRepository.save(emp1);
        employeeRepository.save(emp2);
      }
    }
  }

  private static List<Company> generateCompanies() {
    Company company1 = new Company("Google", "USA");
    Company company2 = new Company("Amazon", "USA");

    List<Company> companies = new ArrayList<>();
    companies.add(company1);
    companies.add(company2);

    return companies;
  }

  private static List<Salary> generateSalaries() {
    Salary currentSalary = new Salary(2000.00, true);
    Salary historicalSalary1 = new Salary(1500.00, false);
    Salary historicalSalary2 = new Salary(900.00, false);
    return  List.of(currentSalary, historicalSalary1, historicalSalary2);
  }
}
