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

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ExpenseService(
            ExpenseRepository expenseRepository,
            ExpenseMapper expenseMapper,
            CategoryRepository categoryRepository,
            UserRepository userRepository
    ) {
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }


    private Users getLoggedInUser() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }


    public ExpenseResponseDTO addExpense(@Valid ExpenseRequestDTO dto) {

        Users user = getLoggedInUser();

        Category category = categoryRepository
                .findByUserAndName(user, dto.getCategoryName().trim().toUpperCase())
                .orElseThrow(CategoryNotFoundException::new);

        Expense expense = expenseMapper.toEntity(dto);
        expense.setDate(LocalDateTime.now());
        expense.setUser(user);
        expense.setCategory(category);

        expenseRepository.save(expense);
        return expenseMapper.toDTO(expense);
    }



    public Page<ExpenseResponseDTO> getAllExpenses(Pageable pageable) {
        Users user = getLoggedInUser();
        return expenseRepository.findByUser(user, pageable)
                .map(expenseMapper::toDTO);
    }

    public ExpenseResponseDTO getExpenseById(int id) {
        Users user = getLoggedInUser();

        Expense expense = expenseRepository
                .findByUserAndId(user, id)
                .orElseThrow(ExpenseNotFoundException::new);

        return expenseMapper.toDTO(expense);
    }

    public Page<ExpenseResponseDTO> getExpensesByType(
            ExpenseType type, Pageable pageable
    ) {
        Users user = getLoggedInUser();
        return expenseRepository.findByUserAndType(user, type, pageable)
                .map(expenseMapper::toDTO);
    }

    public Page<ExpenseResponseDTO> getExpensesByCategory(
            String categoryName, Pageable pageable
    ) {
        Users user = getLoggedInUser();
        return expenseRepository
                .findByUserAndCategory_Name(user, categoryName.trim().toUpperCase(), pageable)
                .map(expenseMapper::toDTO);
    }

    public Page<ExpenseResponseDTO> getExpensesByTitle(
            String title, Pageable pageable
    ) {
        Users user = getLoggedInUser();
        return expenseRepository.findByUserAndTitle(user, title, pageable)
                .map(expenseMapper::toDTO);
    }



    public ExpenseResponseDTO updateExpenseById(
            int id, @Valid ExpenseRequestDTO dto
    ) {
        Users user = getLoggedInUser();

        Expense expense = expenseRepository
                .findByUserAndId(user, id)
                .orElseThrow(ExpenseNotFoundException::new);

        Category category = categoryRepository
                .findByUserAndName(user, dto.getCategoryName().trim().toUpperCase())
                .orElseThrow(CategoryNotFoundException::new);

        expense.setTitle(dto.getTitle());
        expense.setDescription(dto.getDescription());
        expense.setAmount(dto.getAmount());
        expense.setType(dto.getType());
        expense.setCategory(category);

        expenseRepository.save(expense);
        return expenseMapper.toDTO(expense);
    }


    public void deleteExpenseById(int id) {
        Users user = getLoggedInUser();

        Expense expense = expenseRepository
                .findByUserAndId(user, id)
                .orElseThrow(ExpenseNotFoundException::new);

        expenseRepository.delete(expense);
    }
}
