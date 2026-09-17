package com.yarenkaplan.productinventory.controller;

import com.yarenkaplan.productinventory.dto.ProductResponseDTO;
import com.yarenkaplan.productinventory.entity.Product;
import com.yarenkaplan.productinventory.requests.product.CreateProductRequest;
import com.yarenkaplan.productinventory.requests.product.UpdateProductRequest;
import com.yarenkaplan.productinventory.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //post
    @PostMapping
    public void createProduct(@Valid @RequestBody CreateProductRequest createProductRequest) {
        productService.createProduct(createProductRequest.getName(), createProductRequest.getDescription(), createProductRequest.getPrice(), createProductRequest.getStock());
    }

    //get all
    @GetMapping
    public List<ProductResponseDTO> findAllProducts() {
        return productService.findAllProducts();
    }

    //get active products
    @GetMapping("/findActiveProducts")
    public List<ProductResponseDTO> findActiveProducts() {
        return productService.findActiveProducts();
    }

    @GetMapping("/findProductsByStockGreaterThan/{stock}")
    public List<ProductResponseDTO> findProductsByStockGreaterThan(@PathVariable Integer stock) {
        return productService.findProductsByStockGreaterThan(stock);
    }

    //get products sorted by price
    @GetMapping("/findProductsSortedByPrice")
    public List<ProductResponseDTO> findProductsSortedByPrice() {
        return productService.findProductsSortedByPrice();
    }

    //get products sorted by price reverse order
    @GetMapping("/findProductsSortedByPriceReverseOrder")
    public List<ProductResponseDTO> findProductsSortedByPriceReverseOrder() {
        return productService.findProductsSortedByPriceReverseOrder();
    }

    @GetMapping("/findProductsByPriceBetween/{minPrice}/{maxPrice}")
    public List<ProductResponseDTO> findProductsByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return productService.findProductsByPriceBetween(minPrice, maxPrice);
    }

    //get by id
    @GetMapping("/{id}")
    public ProductResponseDTO findProductById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @GetMapping("/findProductsByCategoryId/{categoryId}")
    public List<ProductResponseDTO> findProductsByCategoryId(@PathVariable Long categoryId) {
        return productService.findProductsByCategoryId(categoryId);
    }

    @GetMapping("/findProductsByNameContainingIgnoreCase/{name}")
    public List<ProductResponseDTO> findProductsByNameContainingIgnoreCase(@PathVariable String name) {
        return productService.findProductsByNameContainingIgnoreCase(name);
    }

    //put
    @PutMapping("/{id}")
    public void updateProductById(@PathVariable Long id, @RequestBody UpdateProductRequest updateProductRequest) {
        productService.updateProductById(id, updateProductRequest.getName(), updateProductRequest.getDescription(), updateProductRequest.getPrice(), updateProductRequest.getStock(), updateProductRequest.getStatus(), updateProductRequest.getCategoryId());
    }

    //delete
    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @DeleteMapping
    public void deleteAllProducts() {
        productService.deleteAllProducts();
    }
}
