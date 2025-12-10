package com.phasezero.code.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phasezero.code.entities.Product;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long>{

	Optional<Product> findByPartNumber(String partNumber);
	
}
