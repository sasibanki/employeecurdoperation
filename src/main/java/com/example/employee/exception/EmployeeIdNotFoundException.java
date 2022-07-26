package com.example.employee.exception;


import com.example.employee.contants.ErrorResponseConstants;

public class EmployeeIdNotFoundException extends BaseException {

    private static final long serialVersionUID = 1L;

    public EmployeeIdNotFoundException(ErrorResponseConstants employeeIdNotFoundException) {
        super(employeeIdNotFoundException.getStatus() , employeeIdNotFoundException.getStatusCode() , employeeIdNotFoundException.getMessage());
    }

}
