package com.arnex.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Employee implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "employee_id")
  private Long id;

  @Column
  private String firstName;

  @Column
  private String lastName;

  @Column
  private Integer yearsExperience;

  @Transient
  private Double totalCompensation;

  @Column
  private Company company;

  @OneToOne(mappedBy = "employee")
  private EmployeeProfile profile;

  @Column
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "employee_id")
  private List<Salary> salaries = new ArrayList<>();

  public Employee() {
  }

  public Employee(Long id, String firstName, String lastName, Integer yearsExperience, Company company, List<Salary> salaries) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.yearsExperience = yearsExperience;
    this.company = company;
    this.salaries = salaries;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String fName) {
    this.firstName = fName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Integer getYearsExperience() {
    return yearsExperience;
  }

  public void setYearsExperience(Integer yearsExperience) {
    this.yearsExperience = yearsExperience;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public List<Salary> getSalaries() {
    return salaries;
  }

  public void setSalaries(List<Salary> salaries) {
    this.salaries = salaries;
  }
}
