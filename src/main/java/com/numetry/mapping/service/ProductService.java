package com.numetry.mapping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.numetry.mapping.entity.Product;
import com.numetry.mapping.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	 @Autowired
    private final ProductRepository productRepository;
    
   
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }
    
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

	public Product addProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}
	public Product updateProduct(Long productId, Product newProduct) {
	    Product existingProduct = getProductById(productId);
	    existingProduct.setTitle(newProduct.getTitle());
	    existingProduct.setPrice(newProduct.getPrice());
	    existingProduct.setCompareAtPrice(newProduct.getCompareAtPrice());
	    existingProduct.setVendor(newProduct.getVendor());
	    existingProduct.setBadgeText(newProduct.getBadgeText());
	    existingProduct.setImage(newProduct.getImage());
	    existingProduct.setSecondImage(newProduct.getSecondImage());
	    existingProduct.setCategory(newProduct.getCategory());
	    
	    return productRepository.save(existingProduct);
	}

}