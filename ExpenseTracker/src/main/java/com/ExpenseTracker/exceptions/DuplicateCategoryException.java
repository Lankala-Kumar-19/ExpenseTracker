package com.ExpenseTracker.exceptions;

public class DuplicateCategoryException extends RuntimeException{
    DuplicateCategoryException(){
        super();
    }
    public DuplicateCategoryException(String msg){
        super(msg);
    }
}
