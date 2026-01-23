package com.ExpenseTracker.dtos;

import com.ExpenseTracker.ENUMs.ExpenseType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ExpenseRequestDTO {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    @Positive
    private Long amount;

    @NotNull
    private ExpenseType type;

    @NotNull
    private  int categoryId;


}
