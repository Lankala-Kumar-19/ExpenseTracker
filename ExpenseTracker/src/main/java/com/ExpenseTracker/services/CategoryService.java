package com.ExpenseTracker.services;


import com.ExpenseTracker.dtos.CategoryRequestDTO;
import com.ExpenseTracker.dtos.CategoryResponseDTO;
import com.ExpenseTracker.entities.Category;
import com.ExpenseTracker.entities.Users;
import com.ExpenseTracker.exceptions.CategoryNotFoundException;
import com.ExpenseTracker.exceptions.DuplicateCategoryException;
import com.ExpenseTracker.exceptions.UserNotFoundException;
import com.ExpenseTracker.mappers.CategoryMapper;
import com.ExpenseTracker.repos.CategoryRepository;
import com.ExpenseTracker.repos.UserRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final UserRepository userRepository;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.userRepository = userRepository;
    }

    private Users getLoggedInUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    public CategoryResponseDTO createCategory(@Valid CategoryRequestDTO dto) {
        String name = dto.getName().trim().toUpperCase();
        Users user = getLoggedInUser();

        categoryRepository.findByUserAndName(user, name)
                .ifPresent(c -> { throw new DuplicateCategoryException(); });

        Category category = categoryMapper.toEntity(dto);
        category.setName(name);
        category.setUser(user);

        categoryRepository.save(category);
        return categoryMapper.toDTO(category);
    }

    public Page<CategoryResponseDTO> getAllCategories(Pageable pageable) {
        Users user = getLoggedInUser();
        return categoryRepository.findByUser(user, pageable)
                .map(categoryMapper::toDTO);
    }

    public CategoryResponseDTO getCategoryById(int id) {
        Users user = getLoggedInUser();
        Category category = categoryRepository.findByIdAndUser(id, user)
                .orElseThrow(CategoryNotFoundException::new);
        return categoryMapper.toDTO(category);
    }

    public CategoryResponseDTO updateCategory(int id, @Valid CategoryRequestDTO dto) {
        Users user = getLoggedInUser();
        Category category = categoryRepository.findByIdAndUser(id, user)
                .orElseThrow(CategoryNotFoundException::new);

        String newName = dto.getName().trim().toUpperCase();

        if (!category.getName().equalsIgnoreCase(newName)) {
            categoryRepository.findByUserAndName(user, newName)
                    .ifPresent(c -> { throw new DuplicateCategoryException(); });
            category.setName(newName);
        }

        categoryRepository.save(category);
        return categoryMapper.toDTO(category);
    }

    public void deleteCategory(int id) {
        Users user = getLoggedInUser();
        Category category = categoryRepository.findByIdAndUser(id, user)
                .orElseThrow(CategoryNotFoundException::new);
        categoryRepository.delete(category);
    }
}
