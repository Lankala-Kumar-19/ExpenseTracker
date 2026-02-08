package com.ExpenseTracker.exceptions;

public class IllegalAdminOperationException extends RuntimeException{
    public IllegalAdminOperationException(){
        super();
    }
    public IllegalAdminOperationException(String msg){
        super(msg);
    }
}
