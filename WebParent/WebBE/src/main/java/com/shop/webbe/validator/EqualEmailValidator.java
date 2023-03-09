package com.shop.webbe.validator;



import com.shop.webbe.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EqualEmailValidator implements ConstraintValidator<EqualEmailConstraint, String> {
    @Autowired
    private ICustomerRepository customerRepository;
    @Override
    public void initialize(EqualEmailConstraint equalEmailConstraint) {
        ConstraintValidator.super.initialize(equalEmailConstraint);
    }
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if(customerRepository.findEmail(email) != null){
            return false;
        }
        return true;
    }
}