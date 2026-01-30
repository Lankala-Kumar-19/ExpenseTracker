package com.ExpenseTracker.controllers;

import com.ExpenseTracker.ENUMs.ExpenseType;
import com.ExpenseTracker.dtos.ExpenseRequestDTO;
import com.ExpenseTracker.dtos.ExpenseResponseDTO;
import com.ExpenseTracker.services.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<ExpenseResponseDTO> addExpense(@Valid @RequestBody ExpenseRequestDTO dto) {
        return ResponseEntity.status(201).body(expenseService.addExpense(dto));
    }

    @GetMapping
    public ResponseEntity<Page<ExpenseResponseDTO>> getAllExpenses(Pageable pageable) {
        return ResponseEntity.ok(expenseService.getAllExpenses(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> getExpenseById(@PathVariable int id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> updateExpenseById(@PathVariable int id, @Valid @RequestBody ExpenseRequestDTO dto) {
        return ResponseEntity.ok(expenseService.updateExpenseById(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpenseById(@PathVariable int id) {
        expenseService.deleteExpenseById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<Page<ExpenseResponseDTO>> getExpensesByType(@PathVariable ExpenseType type, Pageable pageable) {
        return ResponseEntity.ok(expenseService.getExpensesByType(type, pageable));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Page<ExpenseResponseDTO>> getExpensesByCategory(@PathVariable String category, Pageable pageable) {
        return ResponseEntity.ok(expenseService.getExpensesByCategory(category, pageable));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<Page<ExpenseResponseDTO>> getExpensesByTitle(@PathVariable String title, Pageable pageable) {
        // Optional: implement a service method for this
        return ResponseEntity.ok(expenseService.getExpensesByTitle(title, pageable));
    }
}
