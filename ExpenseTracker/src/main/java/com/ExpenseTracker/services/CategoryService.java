//package com.ExpenseTracker.services;
//
//
//import com.ExpenseTracker.dtos.CategoryRequestDTO;
//import com.ExpenseTracker.entities.Category;
//import com.ExpenseTracker.entities.Users;
//import com.ExpenseTracker.mappers.CategoryMapper;
//import com.ExpenseTracker.repos.CategoryRepository;
//import com.ExpenseTracker.repos.UserRepository;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.validation.Valid;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CategoryService {
//
//    private final CategoryRepository categoryRepository;
//    private final CategoryMapper categoryMapper;
//    private final UserRepository userRepository;
//
//
//    CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper, UserRepository userRepository){
//        this.categoryRepository= categoryRepository;
//        this.userRepository = userRepository;
//        this.categoryMapper = categoryMapper;
//    }
//
//
//    public Category createCategory(@Valid CategoryRequestDTO dto, HttpServletRequest http) {
//        Category category =categoryMapper.toEntity(dto);
//        int id = http.get
//        Users user = userRepository.findByUsername()
//
//    }
//}
