package com.condelar.minhasfinancas.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.condelar.minhasfinancas.exception.RegraNegocioException;
import com.condelar.minhasfinancas.model.repository.UsuarioRepository;
import com.condelar.minhasfinancas.services.UsuarioService;
import com.condelar.minhasfinancas.services.impl.UsuarioServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

	UsuarioService service;
	
	@MockBean
	UsuarioRepository repository;

	@BeforeEach
	public void setUp() {
		service = new UsuarioServiceImpl(repository);
	}

	@Test
	public void deveValidarEmail() {
		String email = "teste@gmail.com";

		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);

		service.validarEmail(email);
	}

	@Test
	public void deveLancarExceptionQuandoExistirEmailCadastrado() {
		
		String email = "teste@gmail.com";
		
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);

		Assertions.assertThatExceptionOfType(RegraNegocioException.class).isThrownBy(() -> service.validarEmail(email));

	}

}
