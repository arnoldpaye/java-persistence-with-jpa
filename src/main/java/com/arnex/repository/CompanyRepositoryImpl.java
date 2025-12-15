package com.arnex.repository;

import com.arnex.entity.Company;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.Optional;

public class CompanyRepositoryImpl implements CompanyRepository {
  private final EntityManager entityManager;

  public CompanyRepositoryImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  @Transactional(
      rollbackOn = IllegalArgumentException.class,
      dontRollbackOn = EntityExistsException.class
  )
  public Optional<Company> save(Company company) {
    try {
      if (company.getId() == null) {
        entityManager.persist(company);
      } else {
        company = entityManager.merge(company);
      }

      return Optional.of(company);
    } catch (Exception e) {
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
  @Transactional(
      rollbackOn = IllegalArgumentException.class,
      dontRollbackOn = EntityExistsException.class
  )
  public void deleteCompany(Company company) {
    try {
      if (entityManager.contains(company)) {
        entityManager.remove(company);
      } else {
        entityManager.merge(company);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
