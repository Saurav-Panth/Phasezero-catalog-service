package com.phasezero.code.services;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.phasezero.code.dto.ResponseStrucutre;
import com.phasezero.code.entities.Product;
import com.phasezero.code.enums.Message;
import com.phasezero.code.repositories.ProductRepository;

@Service
public class ProductService {
	
	private ProductRepository productRepository;
	
	public ResponseEntity<ResponseStrucutre<Product>> saveProduct(Product product){
		ResponseStrucutre<Product> response = new ResponseStrucutre<>();
		
		response.setData(productRepository.save(product));
		response.setMessage(Message.SUCCESS);
		response.setStausCode(HttpStatus.ACCEPTED.value());
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
		
	}
	
}
