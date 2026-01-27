package com.ExpenseTracker.services;


import com.ExpenseTracker.dtos.CategoryRequestDTO;
import com.ExpenseTracker.dtos.CategoryResponseDTO;
import com.ExpenseTracker.entities.Category;
import com.ExpenseTracker.entities.Users;
import com.ExpenseTracker.exceptions.UserNotFoundException;
import com.ExpenseTracker.mappers.CategoryMapper;
import com.ExpenseTracker.repos.CategoryRepository;
import com.ExpenseTracker.repos.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final UserRepository userRepository;


    CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper, UserRepository userRepository){
        this.categoryRepository= categoryRepository;
        this.userRepository = userRepository;
        this.categoryMapper = categoryMapper;
    }


    public CategoryResponseDTO createCategory(@Valid CategoryRequestDTO dto) {
        Category category =categoryMapper.toEntity(dto);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        category.setUser(user);

        categoryRepository.save(category);
        return categoryMapper.toDTO(category);

    }
}
