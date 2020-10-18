package com.condelar.minhasfinancas.services.impl;

import com.condelar.minhasfinancas.model.entity.Usuario;
import com.condelar.minhasfinancas.model.repository.UsuarioRepositor;
import com.condelar.minhasfinancas.services.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepositor usuarioRepository;

	public UsuarioServiceImpl(UsuarioRepositor usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
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
		// TODO Auto-generated method stub

	}

}
