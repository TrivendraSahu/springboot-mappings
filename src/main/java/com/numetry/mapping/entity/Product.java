package com.numetry.mapping.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "category_products")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String title;
	private Double price;
	private Double compareAtPrice;
	private String vendor;
	private String badgeText;
	
	private String image;
	
	private String secondImage;
	
	 @ManyToOne
	 @JsonBackReference
	    @JoinColumn(name = "category_id")
	    private Category category;
	 
		public Product() {
			
		}
		

}
