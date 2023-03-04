package com.condelar.securyti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.condelar.securyti.dto.Login;
import com.condelar.securyti.entity.Usuario;
import com.condelar.securyti.service.UsuarioService;

@RestController
public class UsuarioController {

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UsuarioService service;

	@PostMapping("/cadastrar")
	public String cadastrar(@RequestBody Login login) {

		Usuario ob = new Usuario();
		ob.setLogin(login.login());
		ob.setPassword(encoder.encode(login.password()));
		Integer id = service.save(ob);

		return "Sucesso" + id;
	}
}
