package com.numetry.mapping.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.numetry.mapping.entity.Category;
import com.numetry.mapping.entity.Product;
import com.numetry.mapping.repository.CategoryRepository;
import com.numetry.mapping.repository.ProductRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    
    
    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }
    
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
    
    public Product addProductToCategory(Long categoryId, Product product) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
        product.setCategory(category);
        return productRepository.save(product);
    }

	

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

	public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
    }
	public Category updateCategory(Long categoryId, Category newCategory) {
	    Category existingCategory = getCategoryById(categoryId);
	    existingCategory.setCategoryName(newCategory.getCategoryName());
	    existingCategory.setCategoryProducts(newCategory.getCategoryProducts());
	    // Update other fields if needed
	    return categoryRepository.save(existingCategory);
	}

}

