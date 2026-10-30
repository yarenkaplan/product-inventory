package com.yarenkaplan.productinventory.repository;

import com.yarenkaplan.productinventory.entity.Product;
import com.yarenkaplan.productinventory.enums.InventoryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByCategoryId(Long categoryId);

    List<Product> findProductsByStockGreaterThan(Integer stock);

    List<Product> findProductsByStockGreaterThanEqual(Integer stock);

    List<Product> findProductsByPriceGreaterThanEqual(BigDecimal minPrice);

    List<Product> findProductsByPriceLessThanEqual(BigDecimal maxPrice);

    List<Product> findProductsByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<Product> findProductsByNameContainingIgnoreCase(String name);

    List<Product> findProductsByDescriptionContainingIgnoreCase(String description);

    List<Product> findProductsByStatus(InventoryStatus status);

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.category")
    Page<Product> findAllWithCategory(Pageable pageable);

    List<Product> findAllByOrderByUpdatedAtDesc();
}