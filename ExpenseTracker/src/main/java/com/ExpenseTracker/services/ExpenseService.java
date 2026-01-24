//package com.ExpenseTracker.services;
//
//
//import com.ExpenseTracker.dtos.ExpenseRequestDTO;
//import com.ExpenseTracker.dtos.ExpenseResponseDTO;
//import com.ExpenseTracker.entities.Expense;
//import com.ExpenseTracker.mappers.ExpenseMapper;
//import com.ExpenseTracker.repos.ExpenseRepository;
//import jakarta.validation.Valid;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//public class ExpenseService {
//
//    private final ExpenseRepository expenseRepository;
//    private final ExpenseMapper expenseMapper;
//
//    ExpenseService(ExpenseRepository expenseRepository,ExpenseMapper expenseMapper){
//        this.expenseRepository=expenseRepository;
//        this.expenseMapper = expenseMapper;
//    }
//
//    public Expense addExpense(@Valid ExpenseRequestDTO dto) {
//        Expense expense = expenseMapper.toEntity(dto);
//        expense.setDate(LocalDateTime.now());
//        expense.setCategory();
//        expense.setUser();
//    }
//}
