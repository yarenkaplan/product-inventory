package com.yarenkaplan.productinventory.requests.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateBookRequest {
    private String title;
    private String author;
    private Long isbn;
    private BigDecimal price;
    private Integer stock;
}
