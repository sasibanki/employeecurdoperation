package com.example.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class EmployeeRequest {

    @NotBlank(message = "Missing required Parameter : name")
    private String name;

    @NotBlank(message = "Missing required Parameter : departmentName")
    private String departmentName;

    @NotNull(message = "Missing required Parameter : salary")
    private Double salary;
}
