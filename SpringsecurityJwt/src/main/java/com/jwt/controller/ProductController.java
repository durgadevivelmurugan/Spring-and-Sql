package com.jwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.jwt.entity.Product;
import com.jwt.model.ProductDto;
import com.jwt.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/get")
	public List<Product> getAllProduct(){
		return productService.getall();
	}
	
	@PostMapping("/save")
	public Object saveProduct(@RequestBody ProductDto productdto) {
		return productService.saveProduct(productdto);
	}
	@GetMapping("/id/{pid}")
	public ProductDto getById(@PathVariable int pid) throws Exception {
		return productService.getById(pid);
	}
	@PutMapping("/update")
	public Object updateProduct(@RequestBody ProductDto dto) {
return productService.updateProduct(dto);
	}
	
	@DeleteMapping("/delete/{pid}")
	public Object delete(@PathVariable int pid) {
		return productService.deleteByid(pid);
	}
	
}
