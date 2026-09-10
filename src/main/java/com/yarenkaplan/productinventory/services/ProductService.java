package com.yarenkaplan.productinventory.services;

import com.yarenkaplan.productinventory.dto.ProductResponseDTO;
import com.yarenkaplan.productinventory.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    //CRUD
    void createProduct(String name, String description, BigDecimal price, int stock);

    void updateProductById(Long id, String name, String description, BigDecimal price, int stock, Long categoryId);

    ProductResponseDTO findProductById(Long id);

    List<ProductResponseDTO> findAllProducts();

    void deleteProductById(Long id);

    void deleteAllProducts();
}
