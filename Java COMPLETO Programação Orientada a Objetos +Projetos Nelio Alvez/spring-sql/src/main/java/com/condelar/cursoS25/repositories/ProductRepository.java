package com.condelar.cursoS25.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.condelar.cursoS25.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
