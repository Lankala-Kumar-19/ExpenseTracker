package com.ExpenseTracker.entities;


import com.ExpenseTracker.ENUMs.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true,nullable = false)
    private String username;

    @Column(nullable = false,unique = true)
    private String mail;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Expense> expenses;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Category> categories;

}
