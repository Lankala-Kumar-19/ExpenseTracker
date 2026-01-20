package com.ExpenseTracker.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private LocalDateTime timeStamp;
    private int status;
    private String errorCode;
    private String message;

    public ErrorResponse(LocalDateTime timeStamp, int status, String errorCode, String message) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
    }
}
