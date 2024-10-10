package com.example.nimap.controller;

import com.example.nimap.entity.Product;
import com.example.nimap.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // 1. Get all products with pagination
    @GetMapping
    public Page<Product> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return productService.getAllProducts(pageable);
    }

    // 2. Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // 3. Create a new product (Product needs to be associated with a category)
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestParam Long categoryId,
                                                 @RequestBody Product product) {
        Product createdProduct = productService.createProduct(product, categoryId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    // 4. Update an existing product by ID
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
                                                 @RequestParam Long categoryId,
                                                 @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product, categoryId);
        return ResponseEntity.ok(updatedProduct);
    }

    // 5. Delete product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

