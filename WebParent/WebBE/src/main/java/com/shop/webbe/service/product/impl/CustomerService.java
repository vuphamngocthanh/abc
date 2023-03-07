package com.shop.webbe.service.product.impl;



import com.shop.webbe.repository.ICustomerRepository;
import com.shop.webbe.service.product.ICustomerService;
import com.shop.webcommon.dto.CustomerDto;
import com.shop.webcommon.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<CustomerDto> findAll() {
        Iterable<Customer> entities = customerRepository.findAll();
        return StreamSupport.stream(entities.spliterator(), true)
                .map(entity -> modelMapper.map(entity, CustomerDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public CustomerDto findById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        return modelMapper.map(customer, CustomerDto.class);
    }

    @Override
    public void save(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }


    public String findEmail(String email){
        return customerRepository.findEmail(email);
    }
    public boolean checkEmailExit(String email){ if(findEmail(email)!= null){ return false; } return true; }
}

