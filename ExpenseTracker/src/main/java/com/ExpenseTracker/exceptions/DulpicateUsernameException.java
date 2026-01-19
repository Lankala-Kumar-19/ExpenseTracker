package com.ExpenseTracker.exceptions;

public class DulpicateUsernameException extends RuntimeException{
    public DulpicateUsernameException(){
        super();
    }
    public DulpicateUsernameException(String msg){
        super(msg);
    }
}
