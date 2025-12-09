package com.arnex;

import com.arnex.entity.Company;
import com.arnex.entity.Employee;
import com.arnex.entity.Salary;
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

        // set salary
        employee.setSalary(new Salary(3500.00, true));

        // set company
        employee.setCompany(new Company("MyCompany"));

        Employee employee2 = new Employee();
        employee2.setfName("James");
        employee2.setlName("Doe");
        employee2.setYearsExperience(5);

        // save employees
        employeeRepository.save(employee);
        employeeRepository.save(employee2);

        // update employee
        Optional<Employee> retrievedEmployee = employeeRepository.getEmployeeById(1L);
        if (retrievedEmployee.isPresent()) {
          retrievedEmployee.get().setlName("Johnson");
          employeeRepository.save(retrievedEmployee.get());
        }

        // delete employee
        employeeRepository.deleteEmployee(employee2);
      }
    }
  }
}
