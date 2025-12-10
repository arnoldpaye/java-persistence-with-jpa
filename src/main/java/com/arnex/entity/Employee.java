package com.arnex.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "employees")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Employee implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "employee_id")
  private Long id;

  @Column
  private String fName;

  @Column
  private String lName;

  @Column
  private Integer yearsExperience;

  @Column
  private Salary salary;

  @Column
  private Company company;

  @OneToOne(mappedBy = "employee")
  private EmployeeProfile profile;

  @Transient
  private Double totalCompensation;

  public Employee() {
  }

  public Employee(Long id, String fName, String lName, Integer yearsExperience, Company company, Salary salary) {
    this.id = id;
    this.fName = fName;
    this.lName = lName;
    this.yearsExperience = yearsExperience;
    this.company = company;
    this.salary = salary;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getfName() {
    return fName;
  }

  public void setfName(String fName) {
    this.fName = fName;
  }

  public String getlName() {
    return lName;
  }

  public void setlName(String lName) {
    this.lName = lName;
  }

  public Integer getYearsExperience() {
    return yearsExperience;
  }

  public void setYearsExperience(Integer yearsExperience) {
    this.yearsExperience = yearsExperience;
  }

  public Salary getSalary() {
    return salary;
  }

  public void setSalary(Salary salary) {
    this.salary = salary;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }
}
