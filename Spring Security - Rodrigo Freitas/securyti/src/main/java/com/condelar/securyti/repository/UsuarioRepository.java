package com.condelar.securyti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.condelar.securyti.entity.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	Usuario findByLogin(String login);

}
