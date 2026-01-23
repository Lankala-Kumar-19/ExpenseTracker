package com.ExpenseTracker.dtos;

import com.ExpenseTracker.ENUMs.ExpenseType;
import com.ExpenseTracker.entities.Category;
import com.ExpenseTracker.entities.Users;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExpenseResponseDTO {

    private  int id;

    private String title;

    private String description;

    private Long amount;

    @Enumerated(EnumType.STRING)
    private ExpenseType type;

    private LocalDateTime date;

    private String categoryName;
}
