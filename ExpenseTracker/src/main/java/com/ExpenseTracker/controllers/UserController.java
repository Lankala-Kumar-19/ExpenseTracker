package com.ExpenseTracker.controllers;

import com.ExpenseTracker.dtos.UserRequestDTO;
import com.ExpenseTracker.dtos.UserResponseDTO;
import com.ExpenseTracker.services.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /* ================= REGISTER ================= */

    @PostMapping
    public ResponseEntity<UserResponseDTO> register(
            @Valid @RequestBody UserRequestDTO dto) {

        return ResponseEntity.ok(userService.register(dto));
    }

    /* ================= READ ================= */

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    /* ================= UPDATE ================= */

    @PutMapping("/id/{id}")
    public ResponseEntity<UserResponseDTO> updateUserById(
            @PathVariable int id,
            @Valid @RequestBody UserRequestDTO dto) {

        return ResponseEntity.ok(userService.updateUserById(id, dto));
    }

    @PutMapping("/username/{username}")
    public ResponseEntity<UserResponseDTO> updateUserByUsername(
            @PathVariable String username,
            @Valid @RequestBody UserRequestDTO dto) {

        return ResponseEntity.ok(userService.updateUserByUsername(username, dto));
    }

    /* ================= DELETE ================= */

    @DeleteMapping("/id/{id}")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable int id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @DeleteMapping("/username/{username}")
    public ResponseEntity<UserResponseDTO> deleteUserByUsername(
            @PathVariable String username) {

        return ResponseEntity.ok(userService.deleteUserByUsername(username));
    }
}
