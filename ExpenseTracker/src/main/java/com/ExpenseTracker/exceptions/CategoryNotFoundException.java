package com.ExpenseTracker.exceptions;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(){
        super();
    }
    public CategoryNotFoundException(String msg){
        super(msg);
    }
}
