package com.arnex.repository;

import com.arnex.entity.Salary;

import java.util.Optional;

public interface SalaryRepository {
  Optional<Salary> save(Salary salary);
  Optional<Salary> getSalaryById(Long id);
  void deleteSalary(Salary salary);
}
