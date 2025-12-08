package com.arnex.repository;

import com.arnex.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
  Optional<Employee> save(Employee employee);
  Optional<Employee> getEmployeeById(Long id);
  void deleteEmployee(Employee employee);
  List<Employee> getEmployeesByExperience(Integer yearExperience);
  List<Employee> getEmployeesByExperienceNativeQuery(Integer yearsExperience);
}
