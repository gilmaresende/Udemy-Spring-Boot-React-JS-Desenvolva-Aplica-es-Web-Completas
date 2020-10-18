package com.condelar.minhasfinancas.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condelar.minhasfinancas.exception.RegraNegocioException;
import com.condelar.minhasfinancas.model.entity.Usuario;
import com.condelar.minhasfinancas.model.repository.UsuarioRepositor;
import com.condelar.minhasfinancas.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepositor repository;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepositor repository) {
		super();
		this.repository = repository;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario salvarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validarEmail(String email) {
		boolean existe = repository.existsEmail(email);
		
		if(existe) {
			throw new RegraNegocioException("Email já utilizado!");
		}

	}

}
