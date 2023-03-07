package com.shop.webbe.controller;


import com.shop.webbe.service.ICategoryService;
import com.shop.webcommon.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("")
    public ResponseEntity<?> listCategories(){
        List<CategoryDto> categoryDtos = categoryService.findAll();
        if (categoryDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryDtos, HttpStatus.ACCEPTED);
    }

    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody CategoryDto categoryDto) {
        categoryService.save(categoryDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> saveChange(@RequestBody CategoryDto categoryDto) {
        categoryService.save(categoryDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
