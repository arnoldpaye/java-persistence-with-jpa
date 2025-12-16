package com.arnex.repository;

import com.arnex.entity.Employee;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {
  private final EntityManager entityManager;

  public EmployeeRepositoryImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  /* @Transactional(
      rollbackOn = IllegalArgumentException.class,
      dontRollbackOn = EntityExistsException.class
  ) */
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
  @Transactional(
      rollbackOn = IllegalArgumentException.class,
      dontRollbackOn = EntityExistsException.class
  )
  public void deleteEmployee(Employee employee) {
    try {
      if (entityManager.contains(employee)) {
        entityManager.remove(employee);
      } else {
        entityManager.merge(employee);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public List<Employee> getEmployeesByExperience(Integer yearsExperience) {
    Query query = entityManager.createQuery("SELECT e FROM Employee as e WHERE e.yearsExperience > :yearsExperience ORDER BY e.lastName");
    query.setParameter("yearsExperience", yearsExperience);
    return query.getResultList();
  }

  @Override
  public List<Employee> getEmployeesByExperienceNativeQuery(Integer yearsExperience) {
    //Note: createNativeQuery is a native SQL query that will return the raw data from the database, not the Entity, need to include class name
    Query nativeQuery = entityManager.createNativeQuery("SELECT * FROM employees WHERE yearsExperience > :yearsExperience ORDER BY lastName", Employee.class);
    nativeQuery.setParameter("yearsExperience", yearsExperience);
    return nativeQuery.getResultList();
  }

  @Override
  public List<Employee> getEmployeesByExperienceCriteriaQuery(Integer yearsExperience) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
    Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);

    return entityManager
        .createQuery(criteriaQuery.select(employeeRoot)
            .where(criteriaBuilder.greaterThan(employeeRoot.get("yearsExperience"), yearsExperience)))
        .getResultList();
  }
}
