package com.condelar.cursoS25.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condelar.cursoS25.entities.Product;
import com.condelar.cursoS25.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public List<Product> findAll(){
		return repository.findAll();
	}

	
	public Product finfById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}
}
