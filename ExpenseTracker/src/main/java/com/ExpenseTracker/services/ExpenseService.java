package com.ExpenseTracker.services;


import com.ExpenseTracker.ENUMs.ExpenseType;
import com.ExpenseTracker.dtos.ExpenseRequestDTO;
import com.ExpenseTracker.dtos.ExpenseResponseDTO;
import com.ExpenseTracker.entities.Category;
import com.ExpenseTracker.entities.Expense;
import com.ExpenseTracker.entities.Users;
import com.ExpenseTracker.exceptions.CategoryNotFoundException;
import com.ExpenseTracker.exceptions.ExpenseNotFoundException;
import com.ExpenseTracker.exceptions.UserNotFoundException;
import com.ExpenseTracker.mappers.ExpenseMapper;
import com.ExpenseTracker.repos.CategoryRepository;
import com.ExpenseTracker.repos.ExpenseRepository;
import com.ExpenseTracker.repos.UserRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        Category category = categoryRepository.findByName(dto.getCategoryName()).orElseThrow(CategoryNotFoundException::new);
        expense.setCategory(category);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        expense.setUser(user);
        expenseRepository.save(expense);
        return expenseMapper.toDTO(expense);
    }

    public Page<ExpenseResponseDTO> getAllExpenses(Pageable pageable) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        Page<Expense> expenses = expenseRepository.findByUser(user,pageable);
        return expenses.map(expenseMapper::toDTO);


//        List<Expense> expenseList = user.getExpenses();
//        return (Page<ExpenseResponseDTO>) expenseMapper.toDTOList(expenseList);


    }

    public ExpenseResponseDTO updateExpenseByTitle(String title, @Valid ExpenseRequestDTO dto) {
        Expense expense = expenseRepository.findByTitle(title).orElseThrow(ExpenseNotFoundException::new);

        Category category = categoryRepository.findByName(dto.getCategoryName()).orElseThrow(CategoryNotFoundException::new);
        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());

        expense.setCategory(category);
        expense.setDescription(dto.getDescription());
        expense.setType(dto.getType());

        expenseRepository.save(expense);

        return expenseMapper.toDTO(expense);

    }

    public ExpenseResponseDTO deleteExpenseById(int id) {
        Expense expense = expenseRepository.findById(id).orElseThrow(ExpenseNotFoundException::new);
        expenseRepository.delete(expense);
        return expenseMapper.toDTO(expense);
    }

    public Page<ExpenseResponseDTO> getExpenseByType(ExpenseType type, Pageable pageable) {

        Page<Expense> expenses = expenseRepository.findByType(type,pageable);

        return expenses.map(expenseMapper::toDTO);
    }
}
