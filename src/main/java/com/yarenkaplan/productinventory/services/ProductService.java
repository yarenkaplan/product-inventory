package com.yarenkaplan.productinventory.services;

import com.yarenkaplan.productinventory.dto.ProductResponseDTO;
import com.yarenkaplan.productinventory.entity.Product;
import com.yarenkaplan.productinventory.enums.InventoryStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    //CRUD
    void createProduct(String name, String description, BigDecimal price, int stock);

    void updateProductById(Long id, String name, String description, BigDecimal price, int stock, InventoryStatus status, Long categoryId);

    ProductResponseDTO findProductById(Long id);

    List<ProductResponseDTO> findActiveProducts();

    List<ProductResponseDTO> findProductsSortedByPrice();
    List<ProductResponseDTO> findProductsSortedByPriceReverseOrder();

    List<ProductResponseDTO> findAllProducts();

    void deleteProductById(Long id);

    void deleteAllProducts();
}
