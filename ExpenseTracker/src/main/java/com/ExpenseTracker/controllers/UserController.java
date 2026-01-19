package com.ExpenseTracker.controllers;

import com.ExpenseTracker.dtos.UserRequestDTO;
import com.ExpenseTracker.dtos.UserResponseDTO;
import com.ExpenseTracker.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/")
    public ResponseEntity<UserResponseDTO> register(UserRequestDTO dto){
        return userService.register(dto);
    }

    @GetMapping("/")
    public ResponseEntity<Page<UserResponseDTO>> getAllUsers(Pageable pageable){
        return userService.getAllUsers(pageable);
    }

    @PutMapping("/id")
    public ResponseEntity<UserResponseDTO> updateUserById(@RequestParam int id,UserRequestDTO dto){
        return userService.updateUserById(id,dto);
    }
    @PutMapping("/username")
    public ResponseEntity<UserResponseDTO> updateUserByUsername(@RequestParam String username,UserRequestDTO dto){
        return userService.updateUserByUsername(username,dto);
    }

    @DeleteMapping("/id")
    public ResponseEntity<UserResponseDTO> deleteUser(@RequestParam int id){
        return userService.deleteUser(id);
    }

    @DeleteMapping("/username")
    public ResponseEntity<UserResponseDTO> deleteUserByUsername(@RequestParam String username){
        return userService.deleteUserByUsername(username);
    }

}
