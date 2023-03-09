package com.shop.webbe.validator;


import com.shop.webbe.dto.CustomerDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EqualPasswordValidator implements ConstraintValidator<EqualPasswordConstraint, CustomerDto> {
    @Override
    public void initialize(EqualPasswordConstraint equalPasswordConstraint) {
        ConstraintValidator.super.initialize(equalPasswordConstraint);
    }
    @Override
    public boolean isValid(CustomerDto customerDto, ConstraintValidatorContext context) {
        String password = customerDto.getPassword();
        String confirmPassword = customerDto.getConfirmPassword();
        if(!password.equals(confirmPassword)){
            return false;
        }
        return true;
    }
}