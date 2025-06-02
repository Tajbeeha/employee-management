package com.genspark.employee.employee;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees()
    {
        return employeeRepository.findAll();
    }

    public void addNewEmployee(Employee employee) {
        Optional<Employee> existingEmployee = employeeRepository.findEmployeeByEmail(employee.getEmpEmail());
        if (existingEmployee.isPresent())
        {
            throw new IllegalStateException("Email already registered.");
        }
        employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long empId) {
        return employeeRepository.findById(empId)
                .orElseThrow( () -> new IllegalStateException("Employee with id " + empId + " is not found."));
    }

    public void deleteEmployee(Long empId) {
        boolean existingEmployee = employeeRepository.existsById(empId);

        if (!existingEmployee)
        {
            throw new IllegalStateException("Employee with id " + empId + " is not found.");
        }
        employeeRepository.deleteById(empId);
    }

    @Transactional
    public void updateEmployee(Long empId, String empName, String empEmail) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new IllegalStateException("Employee with id " + empId + " is not found."));

        if ((empName != null) && (!empName.isEmpty()) && !empName.equals(employee.getEmpName()))
        {
            employee.setEmpName(empName);
        }

        if ((empEmail != null) && (!empEmail.isEmpty()) && !empEmail.equals(employee.getEmpEmail()))
        {
            Optional<Employee> existingEmployee = employeeRepository.findEmployeeByEmail(empEmail);

            if(existingEmployee.isPresent())
            {
                throw new IllegalStateException("The email " + empEmail + " is already taken.");
            }
            employee.setEmpEmail(empEmail);
        }
    }
}
