package com.shop.webbe.controller;


import com.shop.webcommon.dto.ProductSizeDto;
import com.shop.webbe.service.product.impl.ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/product-sizes")
public class ProductSizeController {
    @Autowired
    private ProductSizeService productSizeService;
    @GetMapping()
    @RolesAllowed({"ROLE_ADMIN","USER_ROLE"})
    public ResponseEntity<?> listSize(){
        List<ProductSizeDto> productSizeDtos = productSizeService.findAll();
        if (productSizeDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productSizeDtos, HttpStatus.ACCEPTED);
    }

    @PostMapping("/add")
    @RolesAllowed({"ROLE_ADMIN","USER_ROLE"})
    public ResponseEntity<?> save(@RequestBody ProductSizeDto productSizeDto) {
        productSizeService.save(productSizeDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN","USER_ROLE"})
    public ResponseEntity<?> findOneProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productSizeService.findById(id),HttpStatus.OK);
    }
    @PutMapping("/edit")
    @RolesAllowed({"ROLE_ADMIN","USER_ROLE"})
    public ResponseEntity<?> saveChange(@RequestBody ProductSizeDto productSizeDto) {
        productSizeService.save(productSizeDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    @RolesAllowed({"ROLE_ADMIN","USER_ROLE"})
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productSizeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
