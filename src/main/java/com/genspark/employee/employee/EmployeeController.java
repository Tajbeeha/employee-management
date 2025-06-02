package com.genspark.employee.employee;

import com.genspark.employee.employee.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Employee>>> getAllEmployees()
    {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(new ApiResponse<>("success", "All employees' data fetched successfully.", employees));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Employee>> getEmployeeById(@PathVariable("id") Long empId)
    {
        Employee employeeById = employeeService.getEmployeeById(empId);
        return ResponseEntity.ok(new ApiResponse<>("success", "Details of the employee " + empId, employeeById));
    }

    @PostMapping(path = "/add")
    public ResponseEntity<ApiResponse<Void>> registerNewEmployee(@RequestBody Employee employee)
    {
        employeeService.addNewEmployee(employee);
        return ResponseEntity.status(201).body(new ApiResponse<>("success", "Employee " + employee.getEmpName() + " is registered successfully", null));
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<ApiResponse<Void>> updateEmployee(@PathVariable("id") Long empId,
                               @RequestParam(required = false) String empName,
                               @RequestParam(required = false) String empEmail)
    {
        employeeService.updateEmployee(empId, empName, empEmail);
        return ResponseEntity.ok(new ApiResponse<>("success", "Details of employee " + empName + " is updated successfully", null));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(@PathVariable("id") Long empId)
    {
        employeeService.deleteEmployee(empId);
        return ResponseEntity.ok(new ApiResponse<>("success", "Details of employee " + empId + " is deleted successfully.", null));
    }
}
