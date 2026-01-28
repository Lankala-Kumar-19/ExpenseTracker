package com.ExpenseTracker.repos;

import com.ExpenseTracker.ENUMs.ExpenseType;
import com.ExpenseTracker.entities.Expense;
import com.ExpenseTracker.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Integer> {

    Page<Expense> findByUser(Users user, Pageable pageable);

    Optional<Expense> findByTitle(String title);

    Page<Expense> findByType(ExpenseType type, Pageable pageable);
}
