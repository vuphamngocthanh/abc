package com.shop.webbe.controller;



import com.shop.webbe.service.product.impl.ProductSizeService;
import com.shop.webcommon.dto.ProductSizeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/product-sizes")
public class ProductSizeController {
    @Autowired
    private ProductSizeService productSizeService;
    @GetMapping("")
    public ResponseEntity<?> listSize(){
        List<ProductSizeDto> productSizeDtos = productSizeService.findAll();
        if (productSizeDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productSizeDtos, HttpStatus.ACCEPTED);
    }

    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody ProductSizeDto productSizeDto) {
        productSizeService.save(productSizeDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> saveChange(@RequestBody ProductSizeDto productSizeDto) {
        productSizeService.save(productSizeDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productSizeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
