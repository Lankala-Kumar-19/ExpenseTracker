package com.ExpenseTracker.mappers;


import com.ExpenseTracker.dtos.CategoryRequestDTO;
import com.ExpenseTracker.dtos.CategoryResponseDTO;
import com.ExpenseTracker.entities.Category;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryRequestDTO dto);

    CategoryResponseDTO toDTO(Category category);
}
