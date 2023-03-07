package com.shop.webbe.controller;

import com.shop.webbe.service.product.impl.ProductService;
import com.shop.webcommon.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("")
    @RolesAllowed({"ROLE_ADMIN","USER_ROLE"})
    public ResponseEntity<?> list(){
        List<ProductDto> productDtos = productService.findAll();
        if (productDtos.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("no get");
        }
        return new ResponseEntity<>(productDtos, HttpStatus.ACCEPTED);
    }

    @PostMapping("/add")
    public ResponseEntity<ProductDto> save(@RequestBody ProductDto productDto) {
        productService.save(productDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOneProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findById(id),HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<ProductDto> saveChange(@RequestBody ProductDto productDto) {
        productService.save(productDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductDto> delete(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
