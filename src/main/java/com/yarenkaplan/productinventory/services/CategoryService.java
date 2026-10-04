package com.yarenkaplan.productinventory.services;

import com.yarenkaplan.productinventory.dto.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {

    //CRUD
    void createCategory(String name, String description);

    void updateCategoryById(Long id, String name, String description);

    CategoryResponseDTO findCategoryById(Long id);

    List<CategoryResponseDTO> findAllCategories();

    void deleteCategoryById(Long id);

    void deleteAllCategories();
}
