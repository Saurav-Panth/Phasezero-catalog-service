package com.phasezero.code.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.phasezero.code.dto.ResponseStrucutre;
import com.phasezero.code.entities.Product;
import com.phasezero.code.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private ProductService productService;

	@PostMapping
	public ResponseEntity<ResponseStrucutre<Product>> saveProduct(@RequestBody Product product){
		return productService.saveProduct(product);
	}
	
}
