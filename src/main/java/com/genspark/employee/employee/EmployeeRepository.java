package com.genspark.employee.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //SELECT * from employee where empEmail = employee.empMail
    @Query("SELECT emp from Employee emp WHERE emp.empEmail=:empEmail")
    Optional<Employee> findEmployeeByEmail(String empEmail);
}
