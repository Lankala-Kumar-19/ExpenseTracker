package com.ExpenseTracker.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryResponseDTO {

    private long id;
    private String name;
}
