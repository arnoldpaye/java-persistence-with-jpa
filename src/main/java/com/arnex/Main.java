package com.arnex;

import com.arnex.entity.*;
import com.arnex.repository.EmployeeRepositoryImpl;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default")) {
      try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
        EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl(entityManager);

        ActiveEmployee employee = new ActiveEmployee();
        employee.setFirstName("Mary");
        employee.setLastName("Johnson");
        employee.setYearsExperience(20);

        ActiveEmployee employee2 = new ActiveEmployee();
        employee2.setFirstName("John");
        employee2.setLastName("Doe");
        employee2.setYearsExperience(5);

        //set employment history
        employee.setCompanies(generateCompanies());
        employee2.setCompanies(generateCompanies());

        //create an EmployeeProfile and associate it with an Employee
        employee.setProfile(new EmployeeProfile("userName", "password!", "email@email.com", employee, "Software Engineer"));
        employee2.setProfile(new EmployeeProfile("jDoe", "password234", "johndoe@email.com", employee2, "Project Manager"));

        //set salaries
        employee.setSalaries(generateSalaries());
        employee2.setSalaries(generateSalaries());

        //save Employee
        employeeRepository.save(employee);
        employeeRepository.save(employee2);
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
