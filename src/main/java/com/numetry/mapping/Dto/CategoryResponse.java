package com.numetry.mapping.Dto;

import java.util.List;

import lombok.Data;

@Data
public class CategoryResponse {
	private Long id;
    private String categoryName;
    private List<ProductResponse> categoryProducts;
}