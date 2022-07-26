package com.example.employee.service.impl;

import com.example.employee.contants.ErrorResponseConstants;
import com.example.employee.dto.EmployeeRequest;
import com.example.employee.dto.EmployeeUpdateRequest;
import com.example.employee.exception.EmployeeIdNotFoundException;
import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setDepartmentName(employeeRequest.getDepartmentName());
        employee.setSalary(employeeRequest.getSalary());
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployeeById(EmployeeUpdateRequest employeeUpdateRequest , Integer id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isEmpty())
            throw new EmployeeIdNotFoundException(ErrorResponseConstants.NO_RECORD_FOUND_ERROR);

        Employee employee = optionalEmployee.get();
        employee.setName(employeeUpdateRequest.getName());
        employee.setDepartmentName(employeeUpdateRequest.getDepartmentName());
        employee.setSalary(employeeUpdateRequest.getSalary());
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee deleteEmployeeById(Integer id) {

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty())
            throw new EmployeeIdNotFoundException(ErrorResponseConstants.NO_RECORD_FOUND_ERROR);

        employeeRepository.deleteById(id);
        return optionalEmployee.get();

    }


    @Override
    public Employee getEmployeeById(Integer id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty())
            throw new EmployeeIdNotFoundException(ErrorResponseConstants.NO_RECORD_FOUND_ERROR);
        return optionalEmployee.get();
    }

}
