package com.genspark.employee.employee;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Employee {
    @Id
    @SequenceGenerator(
            name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    private Long empId;
    private String empName;
    private String empEmail;
    private LocalDate empDob;
    @Transient
    private Integer empAge;

    public Employee(Long empId, String empName, String empEmail, LocalDate empDob) {
        this.empId = empId;
        this.empName = empName;
        this.empEmail = empEmail;
        this.empDob = empDob;
    }

    public Employee(String empName, String empEmail, LocalDate empDob) {
        this.empName = empName;
        this.empEmail = empEmail;
        this.empDob = empDob;
    }

    public Employee() {
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public LocalDate getEmpDob() {
        return empDob;
    }

    public void setEmpDob(LocalDate empDob) {
        this.empDob = empDob;
    }

    public Integer getEmpAge() {
        return Period.between(this.empDob, LocalDate.now()).getYears();
    }

    public void setEmpAge(Integer empAge) {
        this.empAge = empAge;
    }
}
