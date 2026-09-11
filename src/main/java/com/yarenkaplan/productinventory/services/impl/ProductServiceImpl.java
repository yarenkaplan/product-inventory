package com.yarenkaplan.productinventory.services.impl;

import com.yarenkaplan.productinventory.dto.ProductResponseDTO;
import com.yarenkaplan.productinventory.entity.Category;
import com.yarenkaplan.productinventory.entity.Product;
import com.yarenkaplan.productinventory.enums.InventoryStatus;
import com.yarenkaplan.productinventory.errors.CategoryNotFoundException;
import com.yarenkaplan.productinventory.errors.ProductNotFoundException;
import com.yarenkaplan.productinventory.repository.CategoryRepository;
import com.yarenkaplan.productinventory.repository.ProductRepository;
import com.yarenkaplan.productinventory.services.ProductService;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    //dependency injection
    //loose coupling and testability purposes
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void createProduct(String name, String description, BigDecimal price, int stock) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);

        productRepository.save(product);
    }

    @Override
    public void updateProductById(Long id, String name, String description, BigDecimal price, int stock, Long categoryId) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        if (!product.getName().equals(name) && !name.trim().isBlank() && (name != null)) {
            product.setName(name);
        }

        if (!product.getDescription().equals(description) && !description.trim().isBlank() && (description != null)) {
            product.setDescription(description);
        }

        //if price defined as 0.0, it will be updated
        if (!product.getPrice().equals(price)) {
            product.setPrice(price);
        }

        //if stock defined as 0, it will be updated
        if (!(product.getStock() == stock)) {
            product.setStock(stock);
        }
        if (!(categoryId.intValue() == 0)) {
            Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
            product.setCategory(category);
        }
        productRepository.save(product);
    }

    @Override
    public ProductResponseDTO findProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        return mapToResponseDTO(product);
    }

    private ProductResponseDTO mapToResponseDTO(Product product) {
        Long categoryId = product.getCategory() != null ? product.getCategory().getId() : null;
        return new ProductResponseDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getStock(), categoryId);
    }

    @Override
    public List<ProductResponseDTO> findAllProducts() {
        return productRepository.findAll().stream().map(this::mapToResponseDTO).toList();
    }

    @Override
    public void deleteProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.delete(product);
    }

    @Override
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }
}
