package com.arnex.repository;

import com.arnex.entity.Company;

import java.util.Optional;

public interface CompanyRepository {
  Optional<Company> save(Company company);
  Optional<Company> getCompanyById(Long id);
  void deleteCompany(Company company);
}
