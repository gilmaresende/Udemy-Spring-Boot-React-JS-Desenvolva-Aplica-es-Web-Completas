package com.condelar.securyti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condelar.securyti.entity.Usuario;
import com.condelar.securyti.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository repository;
	
	public Integer save(Usuario ob) {
		return repository.save(ob).getId();
	}

}
