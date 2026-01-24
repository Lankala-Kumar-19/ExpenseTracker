//package com.ExpenseTracker.controllers;
//
//
//import com.ExpenseTracker.dtos.ExpenseRequestDTO;
//import com.ExpenseTracker.dtos.ExpenseResponseDTO;
//import com.ExpenseTracker.entities.Expense;
//import com.ExpenseTracker.services.ExpenseService;
//import jakarta.validation.Valid;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//
//@RestController
//@RequestMapping("/expense")
//public class ExpenseController {
//
//    private final ExpenseService expenseService;
//    ExpenseController(ExpenseService expenseService){
//        this.expenseService = expenseService;
//    }
//
//
//    @PostMapping("/")
//    public ResponseEntity<Expense> addExpense(@Valid @RequestBody ExpenseRequestDTO dto){
//        Expense expense = expenseService.addExpense(dto);
//        return ResponseEntity.ok().body(expense);
//    }
//}
