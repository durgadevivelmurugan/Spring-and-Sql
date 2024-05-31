package com.jwt.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.entity.Product;
import com.jwt.model.ProductDto;
import com.jwt.repository.ProductRepository;
import com.jwt.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository repository;
	
	@Override
	public List<Product> getall() {
		return repository.findAll();
	}

	@Override
	public Object saveProduct(ProductDto productdto) {
		
		Optional<Product>opt=repository.findById(productdto.getPid());
		if(opt.isPresent()) {
			return "already exists";
		}
		Product product=new Product();
		product.setPid(productdto.getPid());
		product.setPname(productdto.getPname());
		product.setPrice(productdto.getPrice());
		
		
		return repository.save(product);
	}

	@Override
	public ProductDto getById(int pid) throws Exception {
		Optional<Product>opt=repository.findById(pid);
		if(opt.isEmpty()) {
			throw new Exception("not exists") ;
		}
		ProductDto productDto=new ProductDto();
		Product pro=opt.get();
		
		productDto.setPid(pro.getPid());
		productDto.setPname(pro.getPname());
		productDto.setPrice(pro.getPrice());
		
		return productDto;
	}

	@Override
	public Object updateProduct(ProductDto dto) {
		Optional<Product>opt=repository.findById(dto.getPid());
		if(opt.isPresent()) {
			Product product=opt.get();
			product.setPid(dto.getPid());
			product.setPname(dto.getPname());
			product.setPrice(dto.getPrice());
			repository.save(product);
			return "updated";
		}
		else {
		return "don't exists";
		}
	}

	@Override
	public Object deleteByid(int pid) {
Optional<Product>opt=repository.findById(pid);
if(opt.isPresent()) {
	repository.deleteById(pid);
	return "deleted sucessfully";
}else {
	return "doesn't exists";
}
		
	}		

	
}
