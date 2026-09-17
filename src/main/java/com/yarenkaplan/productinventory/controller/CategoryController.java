package com.yarenkaplan.productinventory.controller;

import com.yarenkaplan.productinventory.dto.CategoryResponseDTO;
import com.yarenkaplan.productinventory.requests.category.CreateCategoryRequest;
import com.yarenkaplan.productinventory.requests.category.UpdateCategoryRequest;
import com.yarenkaplan.productinventory.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public void createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        categoryService.createCategory(createCategoryRequest.getName(), createCategoryRequest.getDescription());
    }

    @PutMapping("/{id}")
    public void updateCategoryById(@Valid @RequestBody UpdateCategoryRequest updateCategoryRequest, @PathVariable Long id) {
        categoryService.updateCategoryById(id, updateCategoryRequest.getName(), updateCategoryRequest.getDescription());
    }

    @GetMapping("/{id}")
    public CategoryResponseDTO findCategoryById(@PathVariable Long id) {
        return categoryService.findCategoryById(id);
    }

    @GetMapping("/findAllCategories")
    public List<CategoryResponseDTO> findAllCategories() {
        return categoryService.findAllCategories();
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }

    @DeleteMapping("/deleteAllCategories")
    public void deleteAllCategories() {
        categoryService.deleteAllCategories();
    }
}
