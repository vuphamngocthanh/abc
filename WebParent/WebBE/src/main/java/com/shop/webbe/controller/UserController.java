package com.shop.webbe.controller;



import com.shop.webbe.error.ErrorMessage;
import com.shop.webbe.service.impl.UserServiceImpl;
import com.shop.webcommon.dto.UserDto;
import com.shop.webcommon.dto.UserRequestDTO;
import com.shop.webcommon.dto.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ModelMapper modelMapper;
    @GetMapping
    @RolesAllowed({"ROLE_ADMIN","USER_ROLE"})
    public List<UserResponseDTO> getAllUser() {
        return userService.findAll();

    }
    @GetMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN","USER_ROLE"})
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        Optional<UserResponseDTO> user = Optional.ofNullable(userService.findById(id));
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }



    @PostMapping
    @RolesAllowed({"ROLE_ADMIN","USER_ROLE"})
    public ResponseEntity<String> createUser(@RequestBody @Valid UserRequestDTO userDto) {
        userService.save(userDto);
        return ResponseEntity.ok("New user created successfully");
    }

    @PutMapping
    @RolesAllowed({"ROLE_ADMIN","USER_ROLE"})
    public ResponseEntity<UserResponseDTO> editUser(@Valid @RequestBody UserRequestDTO userRequestDTO){

        return ResponseEntity.ok(userService.save(userRequestDTO));
    }


    @DeleteMapping@RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<String> deleteUser(@RequestBody UserDto userDto) {
        userService.remove(userDto.getId());
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