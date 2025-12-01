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
}
