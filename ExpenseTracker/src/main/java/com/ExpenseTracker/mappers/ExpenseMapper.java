package com.ExpenseTracker.mappers;


import com.ExpenseTracker.dtos.ExpenseRequestDTO;
import com.ExpenseTracker.dtos.ExpenseResponseDTO;
import com.ExpenseTracker.entities.Expense;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    Expense toEntity(ExpenseRequestDTO dto);
    ExpenseResponseDTO toDTO(Expense expense);
}

