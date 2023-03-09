package com.shop.webbe.service.product.impl;


import com.shop.webcommon.dto.CategoryDto;
import com.shop.webcommon.dto.ProductColorDto;
import com.shop.webcommon.dto.ProductDto;
import com.shop.webcommon.dto.ProductSizeDto;
import com.shop.webbe.repository.ProductRepository;
import com.shop.webbe.service.product.IProductService;
import com.shop.webcommon.entity.Category;
import com.shop.webcommon.entity.Product;
import com.shop.webcommon.entity.ProductColor;
import com.shop.webcommon.entity.ProductSize;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<ProductDto> findAll() {
        Pageable firstPageWithTwoElements = PageRequest.of(0, 50);
        Page<Product> products = productRepository.findAll(firstPageWithTwoElements);
        List<ProductDto> productDtos = new ArrayList<>();
        products.forEach(p ->{
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(p,productDto);
            CategoryDto categoryDto = new CategoryDto();
            BeanUtils.copyProperties(p.getCategory(),categoryDto);
            List<ProductSizeDto> productSizeDtos = new ArrayList<>();
            p.getProductSizes().forEach(s ->{
                ProductSizeDto productSizeDto = new ProductSizeDto();
                BeanUtils.copyProperties(s,productSizeDto);
                productSizeDtos.add(productSizeDto);
            });
            List<ProductColorDto> productColorDtos = new ArrayList<>();
            p.getProductColors().forEach(c ->{
                ProductColorDto productColorDto = new ProductColorDto();
                BeanUtils.copyProperties(c,productColorDto);
                productColorDtos.add(productColorDto);
            });
            productDto.setCategoryDto(categoryDto);
            productDto.setProductSizeDtos(productSizeDtos);
            productDto.setProductColorDtos(productColorDtos);
            productDtos.add(productDto);
        });
        return productDtos;
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id).get();
        List<ProductColorDto> productColorDtos = new ArrayList<>();
        List<ProductSizeDto> productSizeDtos = new ArrayList<>();
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        CategoryDto categoryDto = modelMapper.map(product.getCategory(), CategoryDto.class);
        for (ProductSize ps : product.getProductSizes()){
            ProductSizeDto productSizeDto = modelMapper.map(ps, ProductSizeDto.class);
            productSizeDtos.add(productSizeDto);
        }
        for (ProductColor pc : product.getProductColors()){
            ProductColorDto productColorDto = modelMapper.map(pc, ProductColorDto.class);
            productColorDtos.add(productColorDto);
        }
        productDto.setCategoryDto(categoryDto);
        productDto.setProductColorDtos(productColorDtos);
        productDto.setProductSizeDtos(productSizeDtos);
        return productDto;

    }

    @Override
    public void save(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        List<ProductColor> productColors = new ArrayList<>();
        for (ProductColorDto pc : productDto.getProductColorDtos()){
            ProductColor productColor = modelMapper.map(pc, ProductColor.class);
            productColors.add(productColor);
        }
        List<ProductSize> productSizes = new ArrayList<>();
        for (ProductSizeDto ps : productDto.getProductSizeDtos()){
            ProductSize productSize = modelMapper.map(ps, ProductSize.class);
            productSizes.add(productSize);
        }
        Category category = modelMapper.map(productDto.getCategoryDto(), Category.class);
        product.setCategory(category);
        product.setProductColors(productColors);
        product.setProductSizes(productSizes);
        productRepository.save(product);
    }


    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> findAllByName(String name) {
        List<Product> products = productRepository.findAllByNameProduct(name);
        List<ProductDto> productDtos = new ArrayList<>();
        products.forEach(p ->{
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(p,productDto);
            CategoryDto categoryDto = new CategoryDto();
            BeanUtils.copyProperties(p.getCategory(),categoryDto);
            List<ProductSizeDto> productSizeDtos = new ArrayList<>();
            p.getProductSizes().forEach(s ->{
                ProductSizeDto productSizeDto = new ProductSizeDto();
                BeanUtils.copyProperties(s,productSizeDto);
                productSizeDtos.add(productSizeDto);
            });
            List<ProductColorDto> productColorDtos = new ArrayList<>();
            p.getProductColors().forEach(c ->{
                ProductColorDto productColorDto = new ProductColorDto();
                BeanUtils.copyProperties(c,productColorDto);
                productColorDtos.add(productColorDto);
            });
            productDto.setCategoryDto(categoryDto);
            productDto.setProductSizeDtos(productSizeDtos);
            productDto.setProductColorDtos(productColorDtos);
            productDtos.add(productDto);
        });
        return productDtos;
    }
}
