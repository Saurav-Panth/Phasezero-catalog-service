package com.phasezero.code.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phasezero.code.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
