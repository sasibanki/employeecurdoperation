package com.example.employee.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ErrorResponse {
    @JsonProperty("status")
    private final String status;

    @JsonProperty("statusCode")
    private final String statusCode;

    @JsonProperty("message")
    private final String message;
}
