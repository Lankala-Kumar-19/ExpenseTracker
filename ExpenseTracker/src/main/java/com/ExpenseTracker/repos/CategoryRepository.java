package com.ExpenseTracker.repos;

import com.ExpenseTracker.entities.Category;
import com.ExpenseTracker.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Page<Category> findByUser(Users user, Pageable pageable);

    Optional<Category> findByName(String name);

    Optional<Category> findByUserAndName(Users user, String name);

    Optional<Category> findByIdAndUser(int id, Users user);

}
