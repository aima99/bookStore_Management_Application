package com.bookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bookStore.global.Global;
import com.bookStore.model.Product;
import com.bookStore.service.ProductService;

@Controller
public class CartController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id) {
		
		Global.cart.add(productService.getProductById(id).get());
		
		return "redirect:/shop";
		
	}
	
	@GetMapping("/cart")
	public String cartGet(Model model) {
		
		model.addAttribute("cartCount", Global.cart.size());
		model.addAttribute("total", Global.cart.stream().mapToDouble(Product::getPrice).sum());
		model.addAttribute("cart", Global.cart);
		
		return "cart";
		
	}
	
	@GetMapping("/cart/remove/remove/{index}")
	public String cartItemRemove(@PathVariable int index) {
		Global.cart.remove(index);
		
		return "redirect:/cart";
	}
	
	@GetMapping("/checkout")
	public String checkout(Model model) {
		
		model.addAttribute("total", Global.cart.stream().mapToDouble(Product::getPrice).sum());
		
		return "checkout";
	}

}
