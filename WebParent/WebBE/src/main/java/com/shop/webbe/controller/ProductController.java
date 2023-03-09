package com.shop.webbe.controller;


import com.shop.webcommon.dto.ProductDto;
import com.shop.webbe.dto.UploadFile;
import com.shop.webbe.error.ErrorMessage;
import com.shop.webbe.service.product.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir")
            + "/src/main/resources/uploads";

    @Autowired
    private ProductService productService;

    private static UploadFile uploadFilename = new UploadFile();

    @GetMapping("")
    @RolesAllowed({"ROLE_ADMIN","USER_ROLE"})
    public ResponseEntity<?> list(@RequestParam("search") String search){
        List<ProductDto> productDtos;
        if (search.isEmpty()){
            productDtos = productService.findAll();
        }else {
            productDtos = productService.findAllByName(search);
        }
        if (productDtos.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productDtos, HttpStatus.ACCEPTED);
    }
    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile avatar) throws Exception {
        StringBuilder avatarFile = new StringBuilder();
        avatarFile.append(avatar.getOriginalFilename());
        Path pathAvatar = Paths.get(UPLOAD_DIRECTORY, avatar.getOriginalFilename());
        Files.write(pathAvatar, avatar.getBytes());

        uploadFilename.setPhoto("/uploads/" + avatarFile);
        return ResponseEntity.ok("Upload File successfully!!!");
    }

    @PostMapping("/add")
    @RolesAllowed({"ROLE_ADMIN","USER_ROLE"})
    public ResponseEntity<String> save(@RequestBody ProductDto productDto) {
        if(uploadFilename.getPhoto() != null || !uploadFilename.getPhoto().equals("")) {
            productDto.setPhoto(uploadFilename.getPhoto());
        }else {
            productDto.setPhoto("/uploads/logo-fashion-shop.jpg");
        }
        productService.save(productDto);
        return ResponseEntity.ok("Create product created successfully");
    }
    @PutMapping
    @RolesAllowed({"ROLE_ADMIN","USER_ROLE"})
    public ResponseEntity<String> update(@RequestBody ProductDto productDto) {
        ProductDto productDtoID = productService.findById(productDto.getId());
        if(uploadFilename.getPhoto() != null || !uploadFilename.getPhoto().equals("")) {
            if (!uploadFilename.getPhoto().toString().equals(productDtoID.getPhoto())) {
                deleteFile(productDtoID);
            }
            productDto.setPhoto(uploadFilename.getPhoto());
        }
        productService.save(productDto);
        return ResponseEntity.ok("Edit product successfully");

    }

    private static void deleteFile(ProductDto productDto) {
        String pathPoster = (System.getProperty("user.dir")) + "/src/main/resources" + productDto.getPhoto();
        File poster = new File(pathPoster);
        String getPathPoster = poster.getPath();
        try{
            Files.delete(Paths.get(getPathPoster));
        }catch (IOException e){
            System.out.println("dont");
        }
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN","USER_ROLE"})
    public ResponseEntity<?> findOneProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findById(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN","USER_ROLE"})
    public ResponseEntity<ProductDto> delete(@PathVariable Long id) {
        ProductDto productDto = productService.findById(id);
        deleteFile(productDto);
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessage>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        List<ErrorMessage> errorMessages = new ArrayList<>();
        errors.forEach((key, value) -> errorMessages.add(new ErrorMessage(key, value)));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
    }
}
