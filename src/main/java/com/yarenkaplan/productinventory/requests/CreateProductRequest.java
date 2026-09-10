package com.yarenkaplan.productinventory.requests;

import com.yarenkaplan.productinventory.entity.Category;
import com.yarenkaplan.productinventory.entity.Product;
import com.yarenkaplan.productinventory.enums.InventoryStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
    private Long categoryId;
}
