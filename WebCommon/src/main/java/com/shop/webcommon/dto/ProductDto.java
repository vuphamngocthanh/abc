package com.shop.webcommon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String nameProduct;
    private String fabricMaterial;
    private String detailedDescription;
    private Integer price;
    private String photo;
    private List<ProductSizeDto> productSizeDtos;
    private List<ProductColorDto> productColorDtos;
    private CategoryDto categoryDto;

}
