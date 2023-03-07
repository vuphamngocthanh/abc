package com.shop.webcommon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDto {
    private Long id;
    private String name;
    private String photo;
    private Integer price;
    private List<ProductSizeDto> productSizes;
    private List<ProductColorDto> productColors;

}
