package com.shop.webbe.service.product.impl;


import com.shop.webcommon.dto.ProductColorDto;
import com.shop.webbe.repository.ProductColorRepository;
import com.shop.webbe.service.product.IProductColorService;
import com.shop.webcommon.entity.ProductColor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductColorService implements IProductColorService {
    @Autowired
    private ProductColorRepository productColorRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<ProductColorDto> findAll() {
        List<ProductColor> productColors = productColorRepository.findAll();
        return productColors.stream()
                .map(productColor -> modelMapper.map(productColor, ProductColorDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductColorDto findById(Long id) {
        ProductColor productColor = productColorRepository.findById(id).get();
        return modelMapper.map(productColor, ProductColorDto.class);
    }

    @Override
    public void save(ProductColorDto productColorDto) {
        ProductColor productColor = modelMapper.map(productColorDto, ProductColor.class);
        productColorRepository.save(productColor);
    }

    @Override
    public void delete(Long id) {
        productColorRepository.deleteById(id);
    }
}
