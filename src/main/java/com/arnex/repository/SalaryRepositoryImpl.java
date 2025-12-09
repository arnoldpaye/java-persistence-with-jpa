package com.arnex.repository;

import com.arnex.entity.Salary;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class SalaryRepositoryImpl implements SalaryRepository {
  private final EntityManager entityManager;

  public SalaryRepositoryImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Optional<Salary> save(Salary salary) {
    try {
      entityManager.getTransaction().begin();
      if (salary.getId() == null) {
        entityManager.persist(salary);
      } else {
        salary = entityManager.merge(salary);
      }
      entityManager.getTransaction().commit();

      return Optional.of(salary);
    } catch (Exception e) {
      if (entityManager.getTransaction().isActive()) {
        entityManager.getTransaction().rollback();
      }
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
  public void deleteSalary(Salary salary) {
    try {
      entityManager.getTransaction().begin();

      if (entityManager.contains(salary)) {
        entityManager.remove(salary);
      } else {
        entityManager.merge(salary);
      }

      entityManager.getTransaction().commit();
    } catch (Exception e) {
      if (entityManager.getTransaction().isActive()) {
        entityManager.getTransaction().rollback();
      }
      e.printStackTrace();
    }
  }
}
