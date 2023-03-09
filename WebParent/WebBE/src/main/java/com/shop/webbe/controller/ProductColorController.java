package com.shop.webbe.controller;


import com.shop.webcommon.dto.ProductColorDto;
import com.shop.webbe.service.product.impl.ProductColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("api/v1/product-colors")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductColorController {

    @Autowired
    private ProductColorService productColorService;
    @GetMapping("")
    @RolesAllowed({"ROLE_ADMIN","USER_ROLE"})
    public ResponseEntity<?> listColor(){
        List<ProductColorDto> productColorDtos = productColorService.findAll();
        if (productColorDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productColorDtos, HttpStatus.ACCEPTED);
    }

    @PostMapping("/add")
    @RolesAllowed({"ROLE_ADMIN","USER_ROLE"})
    public ResponseEntity<?> save(@RequestBody ProductColorDto productColorDto) {
        productColorService.save(productColorDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    @RolesAllowed({"ROLE_ADMIN","USER_ROLE"})
    public ResponseEntity<?> saveChange(@RequestBody ProductColorDto productColorDto) {
        productColorService.save(productColorDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @GetMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN","USER_ROLE"})
    public ResponseEntity<?> findOneProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productColorService.findById(id),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    @RolesAllowed({"ROLE_ADMIN","USER_ROLE"})
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productColorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
