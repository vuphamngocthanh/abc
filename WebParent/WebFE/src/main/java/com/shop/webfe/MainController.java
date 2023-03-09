package com.shop.webfe;


import com.shop.webfe.customer.CustomerRepository;
import com.shop.webfe.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	@Autowired
	ProductService productService;
	@Autowired
	CustomerRepository customerRepository;
	@GetMapping("")
	public ModelAndView viewIndex(){
		return new ModelAndView("index","products",productService.listProduct());
	}
	@GetMapping("/login")
	public String viewLoginPage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
		
		return "redirect:/cart";
	}
	@GetMapping("/cart")
	public ModelAndView viewCart(){
		return new ModelAndView("cart");
	}
}
