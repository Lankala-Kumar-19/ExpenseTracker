package com.ExpenseTracker.controllers;


import com.ExpenseTracker.dtos.CategoryRequestDTO;
import com.ExpenseTracker.dtos.CategoryResponseDTO;
import com.ExpenseTracker.entities.Category;
import com.ExpenseTracker.mappers.CategoryMapper;
import com.ExpenseTracker.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;


    CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;

    }


    @PostMapping("/addCategory")
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryRequestDTO dto){
        return ResponseEntity.ok(categoryService.createCategory(dto));
    }

    @GetMapping("/")
    public ResponseEntity<Page<CategoryResponseDTO>> getAllCategories(Pageable pageable){
        return ResponseEntity.ok().body(categoryService.getAllCategories(pageable));
    }

    @GetMapping("/{name}")
    public ResponseEntity<CategoryResponseDTO> getCategoryByName(@PathVariable String name){
        return ResponseEntity.ok(categoryService.getCategoryByName(name));
    }

    @PutMapping("{name}")
    public ResponseEntity<CategoryResponseDTO> updateCategoryByName(@PathVariable String name,@Valid @RequestBody CategoryRequestDTO dto){
        return ResponseEntity.ok(categoryService.updateCategoryByName(name,dto));
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteCategory(@PathVariable String name){
        return ResponseEntity.ok(categoryService.deleteCategory(name));
    }
}
