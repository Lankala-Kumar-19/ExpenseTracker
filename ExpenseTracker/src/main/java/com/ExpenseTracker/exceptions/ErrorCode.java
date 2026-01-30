package com.ExpenseTracker.exceptions;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // User errors
    USER_NOT_FOUND(HttpStatus.NOT_FOUND),
    DUPLICATE_USERNAME(HttpStatus.BAD_REQUEST),

    // Expense errors
    EXPENSE_NOT_FOUND(HttpStatus.NOT_FOUND),

    // Category errors
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND),
    DUPLICATE_CATEGORY(HttpStatus.BAD_REQUEST),

    // Generic / framework errors
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED),
    FORBIDDEN(HttpStatus.FORBIDDEN),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR);

    private final HttpStatus httpStatus;

    ErrorCode(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
