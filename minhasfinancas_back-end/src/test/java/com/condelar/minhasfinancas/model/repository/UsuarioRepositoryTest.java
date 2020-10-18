package com.condelar.minhasfinancas.model.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.condelar.minhasfinancas.model.entity.Usuario;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository repository;

	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
		String nome = "teste";
		String email = "teste@gmail.com";

		Usuario user = Usuario.builder().nome(nome).email(email).build();
		repository.save(user);

		boolean result = repository.existsByEmail(email);

		Assertions.assertThat(result).isTrue();

	}
	
	@Test void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComEmail() {
		
		repository.deleteAll();
		
		String email = "teste@gmail.com";
		
		boolean result = repository.existsByEmail(email);

		Assertions.assertThat(result).isFalse();	
	}

}
