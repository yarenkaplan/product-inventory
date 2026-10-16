package com.yarenkaplan.productinventory.services;

import com.yarenkaplan.productinventory.dto.ProductResponseDTO;
import com.yarenkaplan.productinventory.enums.InventoryStatus;
import com.yarenkaplan.productinventory.requests.product.CreateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    //CRUD
    void createProduct(CreateProductRequest createProductRequest);

    void updateProductById(Long id, String name, String description, BigDecimal price, int stock, InventoryStatus status, Long categoryId);

    ProductResponseDTO findProductById(Long id);

    List<ProductResponseDTO> findProductsByCategoryId(Long categoryId);

    List<ProductResponseDTO> findProductsByNameContainingIgnoreCase(String name);

    List<ProductResponseDTO> findActiveProducts();

    List<ProductResponseDTO> findProductsByStockGreaterThan(Integer stock);

    List<ProductResponseDTO> findProductsSortedByPrice();
    List<ProductResponseDTO> findProductsSortedByPriceReverseOrder();

    List<ProductResponseDTO> findProductsByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    Page<ProductResponseDTO> findAllProducts(Pageable pageable);

    List<ProductResponseDTO> findProductsByOrderByUpdatedAtDesc();

    Page<ProductResponseDTO> findAllWithCategory(Pageable pageable);

    InventoryStatus[] findAllProductStatuses();

    void deleteProductById(Long id);

    void deleteAllProducts();
}
