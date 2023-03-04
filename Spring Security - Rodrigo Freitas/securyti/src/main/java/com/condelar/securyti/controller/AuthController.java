package com.condelar.securyti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.condelar.securyti.dto.Login;
import com.condelar.securyti.entity.Usuario;
import com.condelar.securyti.service.TokenService;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	public String login(@RequestBody Login login) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login.login(),
				login.password());

		Authentication authentication = this.authenticationManager.authenticate(authenticationToken);

		var usuario = (Usuario) authentication.getPrincipal();

		return tokenService.gerarToken(usuario);

	}

}
