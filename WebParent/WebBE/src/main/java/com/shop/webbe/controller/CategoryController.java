package com.shop.webbe.controller;


import com.shop.webcommon.dto.CategoryDto;
import com.shop.webbe.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("")
    @RolesAllowed({"ROLE_ADMIN","USER_ROLE"})
    public ResponseEntity<?> listCategories(){
        List<CategoryDto> categoryDtos = categoryService.findAll();
        if (categoryDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryDtos, HttpStatus.ACCEPTED);
    }

    @PostMapping("/add")
    @RolesAllowed({"ROLE_ADMIN","USER_ROLE"})
    public ResponseEntity<?> save(@RequestBody CategoryDto categoryDto) {
        categoryService.save(categoryDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN","USER_ROLE"})
    public ResponseEntity<?> findOneProduct(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.findById(id),HttpStatus.OK);
    }
    @PutMapping("/edit")
    public ResponseEntity<?> saveChange(@RequestBody CategoryDto categoryDto) {
        categoryService.save(categoryDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    @RolesAllowed({"ROLE_ADMIN","USER_ROLE"})
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
