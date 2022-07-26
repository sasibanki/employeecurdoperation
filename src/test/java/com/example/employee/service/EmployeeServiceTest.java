package com.example.employee.service;

import com.example.employee.dto.EmployeeRequest;
import com.example.employee.dto.EmployeeUpdateRequest;
import com.example.employee.exception.EmployeeIdNotFoundException;
import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void when_create_employee_then_return_Employee() {

        when(employeeRepository.save(any(Employee.class))).thenReturn(getEmployee());
        Employee employee = employeeService.createEmployee(getEmployeeRequest());
        assertThat(employee).isNotNull();
    }

    @Test
    public void when_get_employee_by_id_then_return_Employee() {
        Optional<Employee> optionalEmployee = Optional.of(getEmployee());
        when(employeeRepository.findById(any(Integer.class))).thenReturn(optionalEmployee);
        Employee employee = employeeService.getEmployeeById(getEmployee().getId());
        assertThat(employee).isNotNull();
    }

    @Test
    public void when_get_employee_by_invalid_id_then_return_Record_not_found() {
        Optional<Employee> optionalEmployee = Optional.empty();
        when(employeeRepository.findById(any(Integer.class))).thenReturn(optionalEmployee);
        assertThrows(EmployeeIdNotFoundException.class , () -> employeeService.getEmployeeById(getEmployee().getId()));
    }

    @Test
    public void when_delete_employee_by_id_then_return_Employee() {
        Optional<Employee> optionalEmployee = Optional.of(getEmployee());
        when(employeeRepository.findById(any(Integer.class))).thenReturn(optionalEmployee);
        Mockito.doNothing().when(employeeRepository).deleteById(any(Integer.class));
        Employee employee = employeeService.deleteEmployeeById(getEmployee().getId());
        assertThat(employee).isNotNull();
    }

    @Test
    public void when_delete_employee_by_invalid_id_then_return_Record_not_found() {
        Optional<Employee> optionalEmployee = Optional.empty();
        when(employeeRepository.findById(any(Integer.class))).thenReturn(optionalEmployee);
        assertThrows(EmployeeIdNotFoundException.class , () -> employeeService.deleteEmployeeById(getEmployee().getId()));
    }

    @Test
    public void when_update_employee_by_id_then_return_Employee() {
        Optional<Employee> optionalEmployee = Optional.of(getEmployee());
        when(employeeRepository.findById(any(Integer.class))).thenReturn(optionalEmployee);
        when(employeeRepository.save(any(Employee.class))).thenReturn(getEmployee());
        Employee employee = employeeService.updateEmployeeById(getUpdateEmployee() , getEmployee().getId());
        assertThat(employee).isNotNull();
    }

    @Test
    public void when_update_employee_by_invalid_id_then_return_Record_not_found() {
        Optional<Employee> optionalEmployee = Optional.empty();
        when(employeeRepository.findById(any(Integer.class))).thenReturn(optionalEmployee);
        assertThrows(EmployeeIdNotFoundException.class , () -> employeeService.updateEmployeeById(getUpdateEmployee() , getEmployee().getId()));
    }

    private Employee getEmployee() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("test");
        employee.setSalary(100D);
        employee.setDepartmentName("IT");
        return employee;
    }

    private EmployeeUpdateRequest getUpdateEmployee() {
        EmployeeUpdateRequest employee = new EmployeeUpdateRequest();
        employee.setId(1);
        employee.setName("test1");
        employee.setSalary(101D);
        employee.setDepartmentName("IT");
        return employee;
    }

    private EmployeeRequest getEmployeeRequest() {
        EmployeeRequest employee = new EmployeeRequest();
        employee.setName("test");
        employee.setSalary(100D);
        employee.setDepartmentName("IT");
        return employee;
    }
}