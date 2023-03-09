package com.shop.webbe.service.product.impl;


import com.shop.webcommon.dto.ProductSizeDto;
import com.shop.webbe.repository.ProductSizeRepository;
import com.shop.webbe.service.product.IProductSizeService;
import com.shop.webcommon.entity.ProductSize;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductSizeService implements IProductSizeService {
    @Autowired
    private ProductSizeRepository productSizeRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<ProductSizeDto> findAll() {
        List<ProductSize> productSizes = productSizeRepository.findAll();
        return productSizes.stream()
                .map(productSize -> modelMapper.map(productSize, ProductSizeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductSizeDto findById(Long id) {
        ProductSize productSize = productSizeRepository.findById(id).get();
        return modelMapper.map(productSize, ProductSizeDto.class);
    }

    @Override
    public void save(ProductSizeDto productSizeDto) {
        ProductSize productSize = modelMapper.map(productSizeDto, ProductSize.class);
        productSizeRepository.save(productSize);
    }

    @Override
    public void delete(Long id) {
        productSizeRepository.deleteById(id);
    }
}
