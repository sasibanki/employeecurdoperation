package com.example.employee.controller;

import com.example.employee.EmployeeApplication;
import com.example.employee.dto.EmployeeRequest;
import com.example.employee.dto.EmployeeUpdateRequest;
import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = EmployeeApplication.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void whenFindById_thenReturn_Employee() throws Exception {
        Employee employee = getEmployee();
        doReturn(employee).when(employeeService).getEmployeeById(anyInt());
        this.mockMvc.perform(get("/api/v1/employee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name" , is(employee.getName())));
    }

    @Test
    public void create_Employee() throws Exception {
        Employee employee = getEmployee();
        doReturn(employee).when(employeeService).createEmployee(any(EmployeeRequest.class));
        this.mockMvc.perform(post("/api/v1/employee/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(getEmployeeRequest()))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name" , is(employee.getName())));
    }

    @Test
    public void update_Employee() throws Exception {
        Employee employee = getEmployee();
        employee.setName("test1");
        employee.setSalary(101D);
        doReturn(employee).when(employeeService).updateEmployeeById(any(EmployeeUpdateRequest.class) , anyInt());
        this.mockMvc.perform(put("/api/v1/employee/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(getUpdateEmployee()))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name" , is(getUpdateEmployee().getName())));
    }

    @Test
    public void delete_Employee() throws Exception {
        Employee employee = getEmployee();
        employee.setName("test1");
        employee.setSalary(101D);
        doReturn(employee).when(employeeService).deleteEmployeeById(anyInt());
        this.mockMvc.perform(delete("/api/v1/employee/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
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

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}