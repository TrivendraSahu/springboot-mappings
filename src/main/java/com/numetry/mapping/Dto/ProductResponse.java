package com.numetry.mapping.Dto;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String title;
    private Double price;
    private Double compareAtPrice;
    private String vendor;
    private String badgeText;
    private String image;
    private String secondImage;
}