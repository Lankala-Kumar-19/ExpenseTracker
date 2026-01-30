package com.ExpenseTracker.dtos;

import com.ExpenseTracker.exceptions.ErrorCode;
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
    public static ErrorResponse of(ErrorCode errorCode,String message){
        return new ErrorResponse(
                LocalDateTime.now(),
                errorCode.getHttpStatus().value(),
                errorCode.name(),
                message
        );
    }
}
