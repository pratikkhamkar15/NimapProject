package com.example.nimap.controller;

import com.example.nimap.entity.Category;
import com.example.nimap.exception.ResourceNotFoundException;
import com.example.nimap.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest; // Correct import for PageRequest
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // Get all categories with pagination
    public Page<Category> getAllCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page, size); // Initialize PageRequest properly
        return categoryRepository.findAll(pageable);
    }

    // Get category by ID
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Category not found"));
    }

    // Create a new category
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Update an existing category
    public Category updateCategory(Long id, Category updatedCategory) {
        Category category = getCategoryById(id);
        category.setName(updatedCategory.getName());
        return categoryRepository.save(category);
    }

    // Delete a category by ID
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
