package com.example.employee.exception.handler;

import com.example.employee.exception.EmployeeIdNotFoundException;
import com.example.employee.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseExceptionHandler {
/**
 * Handler for EmployeeIdNotFoundException
 */
    @ExceptionHandler(EmployeeIdNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleEmployeeIdNotFoundException(EmployeeIdNotFoundException exception){
        ErrorResponse errorResponse = new ErrorResponse(exception.getStatus() , exception.getStatusCode() , exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
