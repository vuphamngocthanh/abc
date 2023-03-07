package com.shop.webbe.service.product.impl;



import com.shop.webbe.repository.ProductRepository;
import com.shop.webbe.service.product.IProductService;
import com.shop.webcommon.dto.CategoryDto;
import com.shop.webcommon.dto.ProductColorDto;
import com.shop.webcommon.dto.ProductDto;
import com.shop.webcommon.dto.ProductSizeDto;
import com.shop.webcommon.entity.Category;
import com.shop.webcommon.entity.Product;
import com.shop.webcommon.entity.ProductColor;
import com.shop.webcommon.entity.ProductSize;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = StreamSupport.stream(products.spliterator(), true)
                .map(product -> {
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
                }).collect(Collectors.toList());
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
}
