package com.ExpenseTracker.services;


import com.ExpenseTracker.dtos.ExpenseRequestDTO;
import com.ExpenseTracker.dtos.ExpenseResponseDTO;
import com.ExpenseTracker.entities.Category;
import com.ExpenseTracker.entities.Expense;
import com.ExpenseTracker.entities.Users;
import com.ExpenseTracker.exceptions.CategoryNotFoundException;
import com.ExpenseTracker.exceptions.UserNotFoundException;
import com.ExpenseTracker.mappers.ExpenseMapper;
import com.ExpenseTracker.repos.CategoryRepository;
import com.ExpenseTracker.repos.ExpenseRepository;
import com.ExpenseTracker.repos.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    ExpenseService(ExpenseRepository expenseRepository,ExpenseMapper expenseMapper, CategoryRepository categoryRepository, UserRepository userRepository){
        this.expenseRepository=expenseRepository;
        this.expenseMapper = expenseMapper;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public ExpenseResponseDTO addExpense(@Valid ExpenseRequestDTO dto) {
        Expense expense = expenseMapper.toEntity(dto);
        expense.setDate(LocalDateTime.now());
        Category category = categoryRepository.findById((long) dto.getCategoryId()).orElseThrow(CategoryNotFoundException::new);
        expense.setCategory(category);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        expense.setUser(user);
        expenseRepository.save(expense);
        return expenseMapper.toDTO(expense);
    }
}
