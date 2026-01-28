package com.ExpenseTracker.exceptions;

public class ExpenseNotFoundException extends RuntimeException{
    public ExpenseNotFoundException(){
        super();
    }
    public ExpenseNotFoundException(String message){
        super(message);
    }
}
