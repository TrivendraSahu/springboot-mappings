package com.numetry.mapping.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.numetry.mapping.Dto.CategoryResponse;
import com.numetry.mapping.Dto.ProductResponse;
import com.numetry.mapping.entity.Category;
import com.numetry.mapping.entity.Product;
import com.numetry.mapping.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for (Category category : categories) {
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setId(category.getId());
            categoryResponse.setCategoryName(category.getCategoryName());

            List<Product> products = category.getCategoryProducts();
            List<ProductResponse> productResponses = new ArrayList<>();
            for (Product product : products) {
                ProductResponse productResponse = new ProductResponse();
                productResponse.setId(product.getId());
                productResponse.setTitle(product.getTitle());
                productResponse.setPrice(product.getPrice());
                productResponse.setCompareAtPrice(product.getCompareAtPrice());
                productResponse.setVendor(product.getVendor());
                productResponse.setBadgeText(product.getBadgeText());
                productResponse.setImage(product.getImage());
                productResponse.setSecondImage(product.getSecondImage());

                productResponses.add(productResponse);
            }
            categoryResponse.setCategoryProducts(productResponses);
            categoryResponses.add(categoryResponse);
        }
        return categoryResponses;
    }

    
    @PostMapping("/add")
    public Category addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }
    
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok().body(category);
    }
   

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long categoryId, @RequestBody Category newCategory) {
        Category updatedCategory = categoryService.updateCategory(categoryId, newCategory);
        return ResponseEntity.ok().body(updatedCategory);
    }

    
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
