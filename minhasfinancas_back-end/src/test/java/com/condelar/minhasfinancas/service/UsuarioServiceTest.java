package com.condelar.minhasfinancas.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.condelar.minhasfinancas.exception.RegraNegocioException;
import com.condelar.minhasfinancas.model.entity.Usuario;
import com.condelar.minhasfinancas.model.repository.UsuarioRepository;
import com.condelar.minhasfinancas.services.UsuarioService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

	@Autowired
	UsuarioService service;

	@Autowired
	UsuarioRepository repository;

	@Test
	public void deveValidarEmail() {

		String email = "teste@gmail.com";

		repository.deleteAll();
		
		service.validarEmail(email);

	}

	@Test
	public void deveLancarErroQuandoExistirEmailCadastrado() {
		String nome = "teste";
		String email = "teste@gmail.com";

		Usuario usuario = Usuario.builder().nome(nome).email(email).build();
		repository.save(usuario);
		Assertions.assertThatExceptionOfType(RegraNegocioException.class).isThrownBy(() -> service.validarEmail(email));

	}

}
