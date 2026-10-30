package com.yarenkaplan.productinventory.requests.product;

import com.yarenkaplan.productinventory.enums.InventoryStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilterProductRequest {
    private String name;
    private String description;

    @DecimalMin("0.0")
    private BigDecimal minPrice;

    @DecimalMin("0.0")
    private BigDecimal maxPrice;

    @Min(0)
    private Integer stock;
    private InventoryStatus status;
    private Long categoryId;
}