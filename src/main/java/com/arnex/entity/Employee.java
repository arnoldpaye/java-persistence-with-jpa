package com.arnex.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.JOINED)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "employee_company", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "company_id"))
  private List<Company> companies = new ArrayList<>();

  @OneToOne(mappedBy = "employee")
  private EmployeeProfile profile;

  @Column
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "employee_id")
  private List<Salary> salaries = new ArrayList<>();

  public Employee() {
  }

  public Employee(Long id, String firstName, String lastName, Integer yearsExperience, List<Company> companies, List<Salary> salaries) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.yearsExperience = yearsExperience;
    this.companies = companies;
    this.salaries = salaries;
  }

  public Employee(Long id, String firstName, String lastName, Integer yearsExperience, List<Company> companies) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.yearsExperience = yearsExperience;
    this.companies = companies;
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

  public List<Company> getCompanies() {
    return companies;
  }

  public void setCompanies(List<Company> companies) {
    this.companies = companies;
  }

  public List<Salary> getSalaries() {
    return salaries;
  }

  public void setSalaries(List<Salary> salaries) {
    this.salaries = salaries;
  }

  public EmployeeProfile getProfile() {
    return profile;
  }

  public void setProfile(EmployeeProfile profile) {
    this.profile = profile;
  }
}

