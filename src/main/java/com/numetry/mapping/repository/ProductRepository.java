package com.numetry.mapping.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.numetry.mapping.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
}