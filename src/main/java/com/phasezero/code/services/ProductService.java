package com.phasezero.code.services;

import java.util.List;
import java.util.Optional;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.phasezero.code.dto.ResponseStrucutre;
import com.phasezero.code.entities.Product;
import com.phasezero.code.enums.Message;
import com.phasezero.code.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public ResponseEntity<ResponseStrucutre<Product>> saveProduct(Product product){
		
		Optional<Product> opt = productRepository.findByPartNumber(product.getPartNumber());
		
		ResponseStrucutre<Product> response = new ResponseStrucutre<>();
		
		response.setData(productRepository.save(product));
		response.setMessage(Message.CREATED);
		response.setStausCode(HttpStatus.CREATED.value());
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
		
	}
	
	
	public ResponseEntity<ResponseStrucutre<List<Product>>> getProducts(){
		ResponseStrucutre<List<Product>> response = new ResponseStrucutre<>();
		
		response.setData(productRepository.findAll());
		response.setMessage(Message.SUCCESS);
		response.setStausCode(HttpStatus.OK.value());
		
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
	
	
}
