package com.phasezero.code.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.phasezero.code.entities.Product;
import com.phasezero.code.enums.Category;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long>{

	public Optional<Product> findByPartNumber(String partNumber);
	
	public List<Product> findByPartNameContainingIgnoreCase(String name);

	public List<Product> findByCategory(Category category);
	
	public List<Product> findAllByOrderByPriceAsc();
	
	public List<Product> findAll();

	@Query("SELECT SUM(p.price * p.stock) FROM Product p")
	public Double getTotalInventoryValue();
	
	
	Optional<Product> findById(Long id);

	Page<Product> findAll(Pageable pageable);


	
}
