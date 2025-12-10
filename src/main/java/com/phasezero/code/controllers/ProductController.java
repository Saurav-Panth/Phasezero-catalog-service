package com.phasezero.code.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.phasezero.code.dto.ResponseStructure;
import com.phasezero.code.entities.Product;
import com.phasezero.code.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product){
		return productService.saveProduct(product);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Product>>> getProducts(){
		return productService.getProducts();
	}
	
	@GetMapping("/search")
	public ResponseEntity<ResponseStructure<List<Product>>> search(@RequestParam String name) {
	    return productService.searchByPartName(name);
	}

	
}
