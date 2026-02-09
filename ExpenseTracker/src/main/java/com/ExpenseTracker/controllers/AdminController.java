package com.ExpenseTracker.controllers;


import com.ExpenseTracker.ENUMs.Role;
import com.ExpenseTracker.dtos.*;
import com.ExpenseTracker.services.AdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public ResponseEntity<Page<UserResponseDTO>> getAllUsers(Pageable pageable){
        return ResponseEntity.ok(adminService.getAllUsers(pageable));
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<UserResponseDTO> getUserByName(@PathVariable String username){
        return ResponseEntity.ok(adminService.getUserByName(username));
    }

    @PutMapping("/users/{id}/role")
    public ResponseEntity<UserResponseDTO> changeRole(@PathVariable int id, @RequestBody Role role){
        return ResponseEntity.ok(adminService.changeRole(id,role));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id){
        adminService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/expenses")
    public ResponseEntity<Page<ExpenseResponseDTO>> getExpenses(Pageable pageable){
        return ResponseEntity.ok(adminService.getExpenses(pageable));
    }

    @PutMapping("/expenses/{id}")
    public ResponseEntity<ExpenseResponseDTO> updateExpense(@PathVariable int id, @RequestBody ExpenseRequestDTO dto){
        return ResponseEntity.ok(adminService.updateExpense(id,dto));
    }
    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable int id){
        adminService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categories")
    public ResponseEntity<Page<CategoryResponseDTO>> getAllCategories(Pageable pageable){
        return ResponseEntity.ok(adminService.getAllCategories(pageable));
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseDTO> addCategory(@RequestBody CategoryRequestDTO dto){
        return ResponseEntity.ok(adminService.addCategory(dto));
    }

    @PutMapping("/categories/{name}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable String name,@RequestBody CategoryRequestDTO dto){
        return ResponseEntity.ok(adminService.updateCategory(name,dto));
    }

    @DeleteMapping("/categories/{name}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String name){
        adminService.deleteCategory(name);
        return ResponseEntity.noContent().build();
    }
}
