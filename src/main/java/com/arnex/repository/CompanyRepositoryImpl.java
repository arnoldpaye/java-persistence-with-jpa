package com.arnex.repository;

import com.arnex.entity.Company;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class CompanyRepositoryImpl implements CompanyRepository {
  private final EntityManager entityManager;

  public CompanyRepositoryImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Optional<Company> save(Company company) {
    try {
      entityManager.getTransaction().begin();
      if (company.getId() == null) {
        entityManager.persist(company);
      } else {
        company = entityManager.merge(company);
      }
      entityManager.getTransaction().commit();

      return Optional.of(company);
    } catch (Exception e) {
      if (entityManager.getTransaction().isActive()) {
        entityManager.getTransaction().rollback();
      }
      e.printStackTrace();
    }

    return Optional.empty();
  }

  @Override
  public Optional<Company> getCompanyById(Long id) {
    Company company = entityManager.find(Company.class, id);
    return Optional.ofNullable(company);
  }

  @Override
  public void deleteCompany(Company company) {
    try {
      entityManager.getTransaction().begin();

      if (entityManager.contains(company)) {
        entityManager.remove(company);
      } else {
        entityManager.merge(company);
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
