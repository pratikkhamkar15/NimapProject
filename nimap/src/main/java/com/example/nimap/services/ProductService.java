package com.example.nimap.services;

import com.example.nimap.entity.Category;
import com.example.nimap.entity.Product;
import com.example.nimap.repository.CategoryRepository;
import com.example.nimap.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; // Correct import for Pageable
import org.springframework.stereotype.Service;
import com.example.nimap.exception.ResourceNotFoundException;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Get all products with pagination
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    // Get a product by its ID
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product not found"));
    }

    // Create a new product
    public Product createProduct(Product product, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category not found"));
        product.setCategory(category);
        return productRepository.save(product);
    }

    // Update an existing product by its ID
    public Product updateProduct(Long id, Product updatedProduct, Long categoryId) {
        Product product = getProductById(id);
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category not found"));

        product.setName(updatedProduct.getName());
        product.setCategory(category);
        return productRepository.save(product);
    }

    // Delete a product by its ID
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
