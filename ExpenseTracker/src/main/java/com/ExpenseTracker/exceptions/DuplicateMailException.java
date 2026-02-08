package com.ExpenseTracker.exceptions;

public class DuplicateMailException extends RuntimeException{
    public DuplicateMailException(){
        super();
    }
    public DuplicateMailException(String msg){
        super(msg);
    }
}
