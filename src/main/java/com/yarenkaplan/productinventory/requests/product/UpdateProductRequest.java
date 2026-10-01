package com.yarenkaplan.productinventory.requests.product;

import com.yarenkaplan.productinventory.enums.InventoryStatus;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateProductRequest {
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @Positive(message = "Price must be positive!")
    private BigDecimal price;
    private Integer stock;
    private InventoryStatus status;
    private Long categoryId;
}
