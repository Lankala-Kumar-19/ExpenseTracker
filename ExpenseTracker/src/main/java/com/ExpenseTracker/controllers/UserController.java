package com.ExpenseTracker.controllers;

import com.ExpenseTracker.dtos.UserRequestDTO;
import com.ExpenseTracker.dtos.UserResponseDTO;
import com.ExpenseTracker.services.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> register(
            @Valid @RequestBody UserRequestDTO dto) {

        return ResponseEntity.status(201).body(userService.register(dto));
    }



    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @GetMapping("id/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable int id){
        return ResponseEntity.ok(userService.getUserBy_Id(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.getUserByUsernamee(username));
    }

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


    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/username/{username}")
    public ResponseEntity<Void> deleteUserByUsername(
            @PathVariable String username) {
        userService.deleteUserByUsername(username);
        return ResponseEntity.noContent().build();
    }


}
