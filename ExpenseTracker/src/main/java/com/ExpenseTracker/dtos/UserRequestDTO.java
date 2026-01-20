package com.ExpenseTracker.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequestDTO {

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String mail;
    @NotBlank
    private String password;

}
