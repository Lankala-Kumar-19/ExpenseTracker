package com.ExpenseTracker.exceptions;


import com.ExpenseTracker.dtos.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
        return buildError(ErrorCode.USER_NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(DulpicateUsernameException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateUsername(DulpicateUsernameException ex) {
        return buildError(ErrorCode.DUPLICATE_USERNAME, ex.getMessage());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFound(CategoryNotFoundException ex) {
        return buildError(ErrorCode.CATEGORY_NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleExpenseNotFound(ExpenseNotFoundException ex) {
        return buildError(ErrorCode.EXPENSE_NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(DuplicateCategoryException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateCategory(DuplicateCategoryException ex) {
        return buildError(ErrorCode.DUPLICATE_CATEGORY, ex.getMessage());
    }

    private ResponseEntity<ErrorResponse> buildError(ErrorCode errorCode, String message) {
        ErrorResponse errorResponse = ErrorResponse.of(errorCode, message);
        return new ResponseEntity<>(errorResponse, errorCode.getHttpStatus());
    }
}
