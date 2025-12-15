package com.arnex.repository;

import com.arnex.entity.Employee;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {
  private final EntityManager entityManager;

  public EmployeeRepositoryImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Optional<Employee> save(Employee employee) {
    try {
      entityManager.getTransaction().begin();
      if (employee.getId() == null) {
        if (employee.getProfile() != null) {
          entityManager.persist(employee.getProfile());
        }
        entityManager.persist(employee);
      } else {
        employee = entityManager.merge(employee);
      }
      entityManager.getTransaction().commit();

      return Optional.of(employee);
    } catch (Exception e) {
      if (entityManager.getTransaction().isActive()) {
        entityManager.getTransaction().rollback();
      }
      e.printStackTrace();
    }

    return Optional.empty();
  }

  @Override
  public Optional<Employee> getEmployeeById(Long id) {
    Employee employee = entityManager.find(Employee.class, id);
    return Optional.ofNullable(employee);
  }

  @Override
  public void deleteEmployee(Employee employee) {
    try {
      entityManager.getTransaction().begin();

      if (entityManager.contains(employee)) {
        entityManager.remove(employee);
      } else {
        entityManager.merge(employee);
      }

      entityManager.getTransaction().commit();
    } catch (Exception e) {
      if (entityManager.getTransaction().isActive()) {
        entityManager.getTransaction().rollback();
      }
      e.printStackTrace();
    }

  }

  @Override
  public List<Employee> getEmployeesByExperience(Integer yearExperience) {
    return List.of();
  }

  @Override
  public List<Employee> getEmployeesByExperienceNativeQuery(Integer yearsExperience) {
    return List.of();
  }
}
