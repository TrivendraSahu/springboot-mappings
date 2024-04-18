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

import com.numetry.mapping.Dto.ProductResponse;
import com.numetry.mapping.entity.Product;
import com.numetry.mapping.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
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
        return productResponses;
    }
    
    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
    
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        return ResponseEntity.ok().body(product);
    }
    

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product newProduct) {
        Product updatedProduct = productService.updateProduct(productId, newProduct);
        return ResponseEntity.ok().body(updatedProduct);
    }

    
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
