package com.jwt.service;

import java.util.List;

import com.jwt.entity.Product;
import com.jwt.model.ProductDto;

public interface ProductService {

	List<Product> getall();


	Object saveProduct(ProductDto productdto);


	ProductDto getById(int pid) throws Exception;


	Object updateProduct(ProductDto dto);


	Object deleteByid(int pid);

}
