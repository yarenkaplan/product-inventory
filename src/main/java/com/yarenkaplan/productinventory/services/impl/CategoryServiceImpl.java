package com.yarenkaplan.productinventory.services.impl;

import com.yarenkaplan.productinventory.dto.CategoryResponseDTO;
import com.yarenkaplan.productinventory.dto.ProductResponseDTO;
import com.yarenkaplan.productinventory.entity.Category;
import com.yarenkaplan.productinventory.entity.Product;
import com.yarenkaplan.productinventory.errors.CategoryNotFoundException;
import com.yarenkaplan.productinventory.repository.CategoryRepository;
import com.yarenkaplan.productinventory.repository.ProductRepository;
import com.yarenkaplan.productinventory.services.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void createCategory(String name, String description) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);

        categoryRepository.save(category);
    }

    @Override
    public void updateCategoryById(Long id, String name, String description) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));

        if (!category.getName().equals(name) && !name.trim().isBlank() && (name != null)) {
            category.setName(name);
        }

        if (!category.getDescription().equals(description) && !description.trim().isBlank() && (description != null)) {
            category.setDescription(description);
        }

        categoryRepository.save(category);
    }

    @Override
    public CategoryResponseDTO findCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));

        return mapToResponseDTO(category);
    }

    private CategoryResponseDTO mapToResponseDTO(Category category) {
        return new CategoryResponseDTO(category.getId(), category.getName(), category.getDescription());
    }

    @Override
    public List<CategoryResponseDTO> findAllCategories() {
        return categoryRepository.findAll().stream().map(this::mapToResponseDTO).toList();
    }

    @Override
    @Transactional
    public void deleteCategoryById(Long id) {
        //update related products category id as null
        List<Product> products = productRepository.findProductsByCategoryId(id);

        products.forEach(product -> product.setCategory(null));

        //@Transactional is used so saveAll is not necessary
        //productRepository.saveAll(products);

        categoryRepository.deleteById(id);
    }

    @Override
    public void deleteAllCategories() {
        categoryRepository.deleteAll();
    }
}
