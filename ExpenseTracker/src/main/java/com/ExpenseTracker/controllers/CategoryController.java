//package com.ExpenseTracker.controllers;
//
//
//import com.ExpenseTracker.dtos.CategoryRequestDTO;
//import com.ExpenseTracker.entities.Category;
//import com.ExpenseTracker.mappers.CategoryMapper;
//import com.ExpenseTracker.services.CategoryService;
//import jakarta.validation.Valid;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class CategoryController {
//
//    private final CategoryService categoryService;
//
//
//    CategoryController(CategoryService categoryService){
//        this.categoryService = categoryService;
//
//    }
//
//    public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryRequestDTO dto){
//        Category category = categoryService.createCategory(dto);
//        return
//    }
//}
