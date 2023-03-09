package com.shop.webbe.controller;



import com.shop.webbe.dto.CustomerDto;
import com.shop.webbe.service.product.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<Iterable<CustomerDto>> findAll() {
        List<CustomerDto> customers =  customerService.findAll();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable Long id) {
        CustomerDto customerDto =customerService.findById(id);
        if (customerDto!=null) {
            return new ResponseEntity<>(customerDto, HttpStatus.OK);
        }
        String message = "Customer not found! Please try again!";
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/add")
    public ResponseEntity<String> save(@Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult) throws Exception {
        if(!customerService.checkEmailExit(customerDto.getEmail())){
            ResponseEntity.badRequest().body("Email đã tồn tại");
        }
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errors.put(error.getObjectName(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors.toString());
        }
        customerService.save(customerDto);
        return ResponseEntity.ok("Customer created successfully");
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        CustomerDto customerDto1 = customerService.findById(id);
        if (customerDto1!=null) {
            customerService.save(customerDto);
            return  ResponseEntity.ok("Customer edit successfully");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        CustomerDto customerDto = customerService.findById(id);
        if (customerDto!=null) {
            customerService.delete(id);
            return ResponseEntity.ok("Customer delete successfully");
        }
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }
}

