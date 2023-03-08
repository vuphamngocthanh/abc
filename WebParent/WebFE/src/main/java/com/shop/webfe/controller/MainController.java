package com.shop.webfe.controller;

import com.shop.webfe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @Autowired  ProductService productService;
    @GetMapping("")
    public ModelAndView viewIndex(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("products",productService.listProduct());
        return modelAndView;
    }
    @GetMapping("/login")
    public ModelAndView viewLoginPage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return new ModelAndView("login");
        }
        return new ModelAndView("redirect:/");
    }
}
