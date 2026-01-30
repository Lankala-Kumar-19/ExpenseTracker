package com.ExpenseTracker.repos;

import com.ExpenseTracker.ENUMs.ExpenseType;
import com.ExpenseTracker.entities.Category;
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

    Page<Expense> findByUserAndType(Users user, ExpenseType type, Pageable pageable);

    Optional<Expense> findByUserAndId(Users user, int expenseId);

    Page<Expense> findByUserAndCategory_Name(Users user, String categoryName, Pageable pageable);

    Page<Expense> findByUserAndTitle(Users user, String title, Pageable pageable);
}
