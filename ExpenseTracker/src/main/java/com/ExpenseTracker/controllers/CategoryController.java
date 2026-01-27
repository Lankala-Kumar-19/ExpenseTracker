package com.ExpenseTracker.controllers;


import com.ExpenseTracker.dtos.CategoryRequestDTO;
import com.ExpenseTracker.dtos.CategoryResponseDTO;
import com.ExpenseTracker.entities.Category;
import com.ExpenseTracker.mappers.CategoryMapper;
import com.ExpenseTracker.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;


    CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;

    }


    @PostMapping("/addCategory")
    public CategoryResponseDTO createCategory(@Valid @RequestBody CategoryRequestDTO dto){
        return categoryService.createCategory(dto);
    }
}
