package com.yarenkaplan.productinventory.repository;

import com.yarenkaplan.productinventory.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByCategoryId(Long categoryId);

    List<Product> findProductsByStockGreaterThan(Integer stock);

    List<Product> findProductsByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<Product> findProductsByNameContainingIgnoreCase(String name);

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.category")
    Page<Product> findAllWithCategory(Pageable pageable);
 }