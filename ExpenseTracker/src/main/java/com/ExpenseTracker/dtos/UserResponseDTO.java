package com.ExpenseTracker.dtos;

import com.ExpenseTracker.entities.Expense;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class UserResponseDTO {

    private int id;
    private String username;
    private String mail;
    private List<ExpenseResponseDTO> expenses;
}
