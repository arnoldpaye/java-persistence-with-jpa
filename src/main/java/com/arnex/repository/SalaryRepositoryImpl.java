package com.arnex.repository;

import com.arnex.entity.Salary;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.Optional;

public class SalaryRepositoryImpl implements SalaryRepository {
  private final EntityManager entityManager;

  public SalaryRepositoryImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  @Transactional(
      rollbackOn = IllegalArgumentException.class,
      dontRollbackOn = EntityExistsException.class
  )
  public Optional<Salary> save(Salary salary) {
    try {
      if (salary.getId() == null) {
        entityManager.persist(salary);
      } else {
        salary = entityManager.merge(salary);
      }

      return Optional.of(salary);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return Optional.empty();
  }

  @Override
  public Optional<Salary> getSalaryById(Long id) {
    Salary salary = entityManager.find(Salary.class, id);
    return Optional.ofNullable(salary);
  }

  @Override
  @Transactional(
      rollbackOn = IllegalArgumentException.class,
      dontRollbackOn = EntityExistsException.class
  )
  public void deleteSalary(Salary salary) {
    try {
      if (entityManager.contains(salary)) {
        entityManager.remove(salary);
      } else {
        entityManager.merge(salary);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
