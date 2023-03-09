package com.shop.webbe.formarter;


import com.shop.webcommon.dto.CategoryDto;
import com.shop.webbe.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class CategoryDtoFormatter implements Formatter<CategoryDto> {

    private ICategoryService categoryService;

    @Autowired
    public CategoryDtoFormatter(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryDto parse(String text, Locale locale) throws ParseException {
        CategoryDto categoryDto = categoryService.findById(Long.parseLong(text));
        return categoryDto;
    }

    @Override
    public String print(CategoryDto object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName() + "]";
    }
}