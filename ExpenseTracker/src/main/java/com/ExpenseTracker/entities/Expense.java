package com.ExpenseTracker.entities;

import com.ExpenseTracker.ENUMs.ExpenseType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="expenses")
public class Expense {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;

    private Long amount;

    @Enumerated(EnumType.STRING)
    private ExpenseType type;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
}
