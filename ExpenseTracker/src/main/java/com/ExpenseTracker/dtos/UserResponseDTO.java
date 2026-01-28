package com.ExpenseTracker.dtos;

import com.ExpenseTracker.entities.Expense;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {

    private String username;
    private String mail;
    private List<Expense> expenses;
}
