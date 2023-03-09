package com.shop.webcommon.entity;


import com.shop.webcommon.dto.ProductDetailDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private String color;
    private String size;
    private Integer quantity;
    private Float total;
    private ProductDetailDto productDetailDto;
}
