package com.ExpenseTracker.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserRequestDTO {

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String mail;
    @NotBlank
    @Size(min = 8,max = 16,message = "password should be between 8 to 16 characters")
    private String password;



}
