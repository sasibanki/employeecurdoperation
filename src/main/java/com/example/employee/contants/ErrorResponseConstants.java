package com.example.employee.contants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorResponseConstants {
    NO_RECORD_FOUND_ERROR("failed","no_record_found","no record found for the given criteria ");

    private final String status;

    private final String statusCode;

    private final String message;
}
