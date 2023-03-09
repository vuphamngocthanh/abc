package com.shop.webcommon.dto;

import com.shop.webcommon.dto.ProductColorDto;
import com.shop.webcommon.dto.ProductSizeDto;
import lombok.*;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String nameProduct;
    private String fabricMaterial;
    private String detailedDescription;
    private Integer price;
    private String photo;
    private Timestamp createdAt;
    private List<ProductSizeDto> productSizeDtos;


    private List<ProductColorDto> productColorDtos;

    private CategoryDto categoryDto;

}
