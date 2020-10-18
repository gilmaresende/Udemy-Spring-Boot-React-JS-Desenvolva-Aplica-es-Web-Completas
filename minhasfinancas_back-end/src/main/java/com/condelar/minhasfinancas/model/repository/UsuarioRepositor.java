package com.condelar.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.condelar.minhasfinancas.model.entity.Usuario;

public interface UsuarioRepositor extends JpaRepository<Usuario, Long> {
	
	boolean existsEmail(String email);

}
