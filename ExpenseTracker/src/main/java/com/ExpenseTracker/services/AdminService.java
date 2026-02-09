package com.ExpenseTracker.services;


import com.ExpenseTracker.ENUMs.Role;
import com.ExpenseTracker.dtos.*;
import com.ExpenseTracker.entities.Category;
import com.ExpenseTracker.entities.Expense;
import com.ExpenseTracker.entities.Users;
import com.ExpenseTracker.exceptions.CategoryNotFoundException;
import com.ExpenseTracker.exceptions.DuplicateCategoryException;
import com.ExpenseTracker.exceptions.ExpenseNotFoundException;
import com.ExpenseTracker.exceptions.UserNotFoundException;
import com.ExpenseTracker.mappers.CategoryMapper;
import com.ExpenseTracker.mappers.ExpenseMapper;
import com.ExpenseTracker.mappers.UserMapper;
import com.ExpenseTracker.repos.CategoryRepository;
import com.ExpenseTracker.repos.ExpenseRepository;
import com.ExpenseTracker.repos.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;
//    private final ExpenseService expenseService;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public AdminService(UserRepository userRepository,UserMapper userMapper,ExpenseRepository expenseRepository,ExpenseMapper expenseMapper,ExpenseService expenseService,CategoryRepository categoryRepository,CategoryMapper categoryMapper){
        this.userRepository=userRepository;
        this.userMapper=userMapper;
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
//        this.expenseService = expenseService;
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public Page<UserResponseDTO> getAllUsers(Pageable pageable) {

        Page<Users> users = userRepository.findAll(pageable);

        return users.map(userMapper::toDTO);

    }

    public UserResponseDTO changeRole(int id, Role role) {
        Users exist = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("user not found"));

        exist.setRole(role);
        userRepository.save(exist);

        return userMapper.toDTO(exist);
    }

    public void deleteUser(int id) {
        Users exist = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("user not found"));
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Users admin= userRepository.findByUsername(name).orElseThrow(()-> new UserNotFoundException());
        int adminId = admin.getId();
        if(adminId==id) throw new IllegalArgumentException("admin cannot delete himself");

        userRepository.delete(exist);
    }

    public UserResponseDTO getUserByName(String name) {
        Users exist = userRepository.findByUsername(name).orElseThrow(()->new UserNotFoundException("user not found"));

        return userMapper.toDTO(exist);
    }

    public Page<ExpenseResponseDTO> getExpenses(Pageable pageable) {
        Page<Expense> expenses = expenseRepository.findAll(pageable);
        return expenses.map(expenseMapper::toDTO);
    }


    public ExpenseResponseDTO updateExpense(int id, ExpenseRequestDTO dto) {
        Expense expense = expenseRepository.findById(id).orElseThrow(ExpenseNotFoundException::new);
        Category category = categoryRepository.findByName(dto.getCategoryName().toUpperCase()).orElseThrow(()->new CategoryNotFoundException());
//        category.setName(dto.getCategoryName());


        expense.setTitle(dto.getTitle());
        expense.setDescription(dto.getDescription());
        expense.setAmount(dto.getAmount());
        expense.setType(dto.getType());
        expense.setCategory(category);

        expenseRepository.save(expense);
        return expenseMapper.toDTO(expense);
//        return expenseService.updateExpenseById(id,dto);
    }

    public void deleteExpense(int id) {
        Expense exists = expenseRepository.findById(id).orElseThrow(ExpenseNotFoundException::new);
        expenseRepository.delete(exists);
    }


    public Page<CategoryResponseDTO> getAllCategories(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(categoryMapper::toDTO);
    }

    public CategoryResponseDTO addCategory(CategoryRequestDTO dto) {

        dto.setName(dto.getName().toUpperCase());

        categoryRepository.findByName(dto.getName().toUpperCase()).ifPresent(c -> {throw new DuplicateCategoryException();});

        Category category = categoryRepository.save(categoryMapper.toEntity(dto));

        return categoryMapper.toDTO(category);



    }

    public CategoryResponseDTO updateCategory(String name, CategoryRequestDTO dto) {
        name = name.toUpperCase();
        Category category = categoryRepository.findByName(name).orElseThrow(CategoryNotFoundException::new);
        category.setName(dto.getName().toUpperCase());
        categoryRepository.save(category);
        return categoryMapper.toDTO(category);
    }

    public void deleteCategory(String name) {
        Category category = categoryRepository.findByName(name.toUpperCase()).orElseThrow(CategoryNotFoundException::new);
        categoryRepository.delete(category);
    }
}
