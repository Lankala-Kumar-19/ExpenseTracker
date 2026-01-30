package com.ExpenseTracker.mappers;


import com.ExpenseTracker.dtos.ExpenseRequestDTO;
import com.ExpenseTracker.dtos.ExpenseResponseDTO;
import com.ExpenseTracker.entities.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    Expense toEntity(ExpenseRequestDTO dto);
    @Mapping(target = "categoryName", source = "category.name")
    ExpenseResponseDTO toDTO(Expense expense);

    List<ExpenseResponseDTO> toDTOList(List<Expense> expenseList);

}

