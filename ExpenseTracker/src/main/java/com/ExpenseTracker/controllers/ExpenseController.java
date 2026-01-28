package com.ExpenseTracker.controllers;


import com.ExpenseTracker.ENUMs.ExpenseType;
import com.ExpenseTracker.dtos.ExpenseRequestDTO;
import com.ExpenseTracker.dtos.ExpenseResponseDTO;
import com.ExpenseTracker.entities.Expense;
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
    ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }


    @PostMapping("/addExpense")
    public ResponseEntity<ExpenseResponseDTO> addExpense(@Valid @RequestBody ExpenseRequestDTO dto){
        System.out.println(dto.getTitle());
        return ResponseEntity.ok().body(expenseService.addExpense(dto));
    }

    @GetMapping("/")
    public ResponseEntity<Page<ExpenseResponseDTO>> getAllExpenses(Pageable pageable){
        return ResponseEntity.ok(expenseService.getAllExpenses(pageable));

    }

    @PutMapping("/{title}")
    public ResponseEntity<ExpenseResponseDTO> updateExpenseByTitle(@PathVariable String title ,@Valid @RequestBody ExpenseRequestDTO dto){
        return ResponseEntity.ok(expenseService.updateExpenseByTitle(title,dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ExpenseResponseDTO> deleteExpenseById(@PathVariable int id){
        return ResponseEntity.ok(expenseService.deleteExpenseById(id));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<Page<ExpenseResponseDTO>> getExpenseByType(@PathVariable ExpenseType type, Pageable pageable){
        return ResponseEntity.ok(expenseService.getExpenseByType(type,pageable));
    }

}
