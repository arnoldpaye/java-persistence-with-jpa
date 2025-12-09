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

  @Transient
  private Double totalCompensation;

  public Employee() {
  }

  public Employee(Long id, String fName, String lName, Integer yearsExperience) {
    this.id = id;
    this.fName = fName;
    this.lName = lName;
    this.yearsExperience = yearsExperience;
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

  @Override
  public String toString() {
    return "Employee{" +
        "id=" + id +
        ", fName='" + fName + '\'' +
        ", lName='" + lName + '\'' +
        ", yearsExperience=" + yearsExperience +
        ", totalCompensation=" + totalCompensation +
        '}';
  }
}
