package com.example.employee.service;

import com.example.employee.dto.EmployeeRequest;
import com.example.employee.dto.EmployeeUpdateRequest;
import com.example.employee.model.Employee;

public interface EmployeeService {

    Employee createEmployee(EmployeeRequest employeeRequest);

    Employee updateEmployeeById(EmployeeUpdateRequest employeeUpdateRequest , Integer id);

    Employee getEmployeeById(Integer id);

    Employee deleteEmployeeById(Integer id);

}
