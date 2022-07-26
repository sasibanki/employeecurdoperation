package com.example.employee.controller;

import com.example.employee.dto.EmployeeRequest;
import com.example.employee.dto.EmployeeUpdateRequest;
import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/employee")
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/")
    public Employee createEmployee(@RequestBody @Valid EmployeeRequest employeeRequest) {
        return employeeService.createEmployee(employeeRequest);
    }

    @PutMapping("/{id}")
    public Employee updateEmployeeById(@RequestBody @Valid EmployeeUpdateRequest employeeUpdateRequest , @PathVariable Integer id) {
        return employeeService.updateEmployeeById(employeeUpdateRequest , id);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/{id}")
    public Employee deleteEmployeeById(@PathVariable Integer id) {
        return employeeService.deleteEmployeeById(id);
    }
}
