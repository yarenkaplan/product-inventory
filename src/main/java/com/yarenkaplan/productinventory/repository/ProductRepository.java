package com.yarenkaplan.productinventory.repository;

import com.yarenkaplan.productinventory.entity.Product;
import com.yarenkaplan.productinventory.enums.InventoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
}