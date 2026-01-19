package com.ExpenseTracker.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequestDTO {

    @NotNull
    private String username;
    @Email
    private String mail;
    @NotNull
    private String password;

}
