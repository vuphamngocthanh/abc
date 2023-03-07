package com.shop.webbe.controller;


import com.shop.webbe.service.product.impl.ProductColorService;
import com.shop.webcommon.dto.ProductColorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product-colors")
public class ProductColorController {

    @Autowired
    private ProductColorService productColorService;
    @GetMapping("")
    public ResponseEntity<?> listColor(){
        List<ProductColorDto> productColorDtos = productColorService.findAll();
        if (productColorDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productColorDtos, HttpStatus.ACCEPTED);
    }

    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody ProductColorDto productColorDto) {
        productColorService.save(productColorDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> saveChange(@RequestBody ProductColorDto productColorDto) {
        productColorService.save(productColorDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productColorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
