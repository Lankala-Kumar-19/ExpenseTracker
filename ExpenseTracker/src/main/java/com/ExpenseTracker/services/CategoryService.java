package com.ExpenseTracker.services;


import com.ExpenseTracker.dtos.CategoryRequestDTO;
import com.ExpenseTracker.dtos.CategoryResponseDTO;
import com.ExpenseTracker.entities.Category;
import com.ExpenseTracker.entities.Users;
import com.ExpenseTracker.exceptions.CategoryNotFoundException;
import com.ExpenseTracker.exceptions.DulpicateUsernameException;
import com.ExpenseTracker.exceptions.UserNotFoundException;
import com.ExpenseTracker.mappers.CategoryMapper;
import com.ExpenseTracker.repos.CategoryRepository;
import com.ExpenseTracker.repos.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
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


    CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper, UserRepository userRepository){
        this.categoryRepository= categoryRepository;
        this.userRepository = userRepository;
        this.categoryMapper = categoryMapper;
    }


    public CategoryResponseDTO createCategory(@Valid CategoryRequestDTO dto) {
        String name = dto.getName().toUpperCase();

        Category category = categoryRepository.findByName(name).orElse(null);
        if(category!=null) throw new DulpicateUsernameException();

        category = categoryMapper.toEntity(dto);
        category.setName(name);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        category.setUser(user);

        categoryRepository.save(category);
        return categoryMapper.toDTO(category);

    }

    public Page<CategoryResponseDTO> getAllCategories(Pageable pageable) {

        System.out.println("working");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        Page<Category> categories = categoryRepository.findByUser(user,pageable);

        return categories.map(categoryMapper::toDTO);
    }

    public String deleteCategory(String name) {
        name = name.toUpperCase();
        Category existing = categoryRepository.findByName(name).orElseThrow(CategoryNotFoundException::new);
        categoryRepository.delete(existing);

        return  existing.getName();
    }

    public CategoryResponseDTO getCategoryByName(String name) {
        name = name.toUpperCase();
        Category existing = categoryRepository.findByName(name).orElseThrow(CategoryNotFoundException::new);
        return categoryMapper.toDTO(existing);
    }

    public CategoryResponseDTO updateCategoryByName(String name, @Valid CategoryRequestDTO dto) {
        name = name.toUpperCase();
        Category existing = categoryRepository.findByName(name).orElseThrow(CategoryNotFoundException::new);

        existing.setName(dto.getName().toUpperCase());
        categoryRepository.save(existing);

        return categoryMapper.toDTO(existing);


    }
}
