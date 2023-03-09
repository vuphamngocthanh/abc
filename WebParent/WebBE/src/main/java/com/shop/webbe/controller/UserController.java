package com.shop.webbe.controller;


import com.shop.webbe.dto.UploadFile;
import com.shop.webbe.dto.UserRequestDTO;
import com.shop.webbe.dto.UserResponseDTO;
import com.shop.webbe.error.ErrorMessage;
import com.shop.webbe.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir")
            + "/src/main/resources/uploads";

    private static UploadFile uploadFilename = new UploadFile();
    @Autowired
    private UserServiceImpl userService;

    @GetMapping()
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<?> getAllUser(@RequestParam(value = "name", required = false) String name) {
       List<UserResponseDTO> userResponseDTOList;

       if (name.equals("") || name == null) {
           userResponseDTOList = userService.findAll();
       } else {
           userResponseDTOList = userService.findAllByNameContaining(name);
       }
        return new ResponseEntity<>(userResponseDTOList, HttpStatus.OK);




    }

    @GetMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        Optional<UserResponseDTO> user = Optional.ofNullable(userService.findById(id));
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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

    @PostMapping
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<String> createUser(@RequestBody @Valid UserRequestDTO userDto) {
        userDto.setPhoto(uploadFilename.getPhoto());
        userService.save(userDto);
        uploadFilename.setPhoto("/uploads/avatar-default.png");
        return ResponseEntity.ok("New user created successfully");
    }

    @PutMapping
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<String> editUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        userRequestDTO.setPhoto(uploadFilename.getPhoto());
        userService.save(userRequestDTO);
        uploadFilename.setPhoto("/uploads/avatar-default.png");
        return ResponseEntity.ok("Edit User successfully!!!");
    }




    @DeleteMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.remove(id);
        return ResponseEntity.noContent().build();
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