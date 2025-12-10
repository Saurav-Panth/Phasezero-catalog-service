package com.phasezero.code.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.phasezero.code.dto.ResponseStructure;
import com.phasezero.code.exceptions.*;
import com.phasezero.code.repositories.ProductRepository;
import com.phasezero.code.entities.Product;
import com.phasezero.code.enums.Category;
import com.phasezero.code.enums.Message;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product) {

		if (product.getPartNumber() == null || product.getPartNumber().isBlank())
			throw new MissingFieldException("PartNumber is required");

		if (product.getPartName() == null || product.getPartName().isBlank())
			throw new MissingFieldException("PartName is required");

		if (product.getCategory() == null)
			throw new MissingFieldException("Category is required");

		if (product.getPrice() == null)
			throw new MissingFieldException("Price is required");

		if (product.getPrice() < 0.0)
			throw new NegativePriceException("Price cannot be negative");

		if (product.getStock() == null)
			throw new MissingFieldException("Stock is required");

		if (product.getStock() < 0)
			throw new NegativeStockException("Stock cannot be negative");

		if (product.getBrand() == null || product.getBrand().isBlank())
			throw new MissingFieldException("Brand is required");

		if (productRepository.findByPartNumber(product.getPartNumber()).isPresent())
			throw new DuplicatePartNumberException("Duplicate PartNumber");

		ResponseStructure<Product> response = new ResponseStructure<>();

		response.setData(productRepository.save(product));
		response.setMessage(Message.CREATED);
		response.setStausCode(HttpStatus.CREATED.value());

		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<List<Product>>> getProducts() {

		List<Product> list = productRepository.findAll();
		if (list.isEmpty())
			throw new NoRecordException("PRODUCTS NOT FOUND");

		ResponseStructure<List<Product>> response = new ResponseStructure<>();
		response.setData(list);
		response.setMessage(Message.SUCCESS);
		response.setStausCode(HttpStatus.OK.value());

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	public ResponseEntity<ResponseStructure<List<Product>>> searchByPartName(String name) {

		if (name == null || name.isBlank())
			throw new MissingFieldException("Search keyword is required");

		List<Product> products = productRepository.findByPartNameContainingIgnoreCase(name);

		if (products.isEmpty())
			throw new NoRecordException("No products found for: " + name);

		ResponseStructure<List<Product>> response = new ResponseStructure<>();
		response.setData(products);
		response.setMessage(Message.SUCCESS);
		response.setStausCode(HttpStatus.OK.value());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> getByCategory(String cate) {

	    if (!isValidCategory(cate)) {
	        String allowed = Arrays.toString(Category.values());
	        throw new NoRecordException("Invalid category: " + cate + "   Allowed : " + allowed);
	    }

	    Category category = Category.valueOf(cate.toUpperCase());
	    List<Product> list = productRepository.findByCategory(category);

	    if (list.isEmpty()) {
	        throw new NoRecordException("No products found for category: " + category);
	    }

	    ResponseStructure<List<Product>> response = new ResponseStructure<>();
	    response.setData(list);
	    response.setMessage(Message.SUCCESS);
	    response.setStausCode(HttpStatus.OK.value());

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}


	static private boolean isValidCategory(String cat) {
	    for (Category c : Category.values()) {
	        if (c.name().equalsIgnoreCase(cat)) return true;
	    }
	    return false;
	}
	
	
	public ResponseEntity<ResponseStructure<List<Product>>> getAllSortedByPrice() {
	    
	    List<Product> list = productRepository.findAllByOrderByPriceAsc();

	    if (list.isEmpty()) {
	        throw new NoRecordException("No products available");
	    }

	    ResponseStructure<List<Product>> response = new ResponseStructure<>();
	    response.setData(list);
	    response.setMessage(Message.SUCCESS);
	    response.setStausCode(HttpStatus.OK.value());

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

	
	public ResponseEntity<ResponseStructure<Double>> getTotalInventoryValue() {

	    Double value = productRepository.getTotalInventoryValue();
	    if (value == null) value = 0.0;

	    ResponseStructure<Double> response = new ResponseStructure<>();
	    response.setData(value);
	    response.setMessage(Message.SUCCESS);
	    response.setStausCode(HttpStatus.OK.value());

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}



}










