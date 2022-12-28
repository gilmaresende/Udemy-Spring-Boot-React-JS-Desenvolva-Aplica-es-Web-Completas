package com.condelar.minhasfinancas.model.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.condelar.minhasfinancas.model.entity.Usuario;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository repository;

	@Autowired
	TestEntityManager entityManeger;

	@Test
	public void deveVerificarAExistenciaDeUmEmail() {

		String email = "teste@gmail.com";

		Usuario user = criarUsuario();

		entityManeger.persist(user);
		boolean result = repository.existsByEmail(email);

		Assertions.assertThat(result).isTrue();

	}

	@Test
	void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComEmail() {

		String email = "teste@gmail.com";

		boolean result = repository.existsByEmail(email);

		Assertions.assertThat(result).isFalse();
	}

	@Test
	public void devePersisitrUmUsuarioNaBaseDeDados() {

		Usuario user = criarUsuario();

		Usuario usuarioSalvo = repository.save(user);

		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();

	}

	@Test
	void deveBuscarUsuarioPorEmail() {
		String email = "teste@gmail.com";

		Usuario user = criarUsuario();

		entityManeger.persist(user);

		Optional<Usuario> result = repository.findByEmail(email);

		Assertions.assertThat(result.isPresent()).isTrue();
	}

	@Test
	void deveRetornarAoBuscarUsuarioPorEmailQuandoNaoExisteNaBase() {

		String email = "teste@gmail.com";

		Optional<Usuario> result = repository.findByEmail(email);

		Assertions.assertThat(result.isPresent()).isFalse();
	}

	public static Usuario criarUsuario() {
		Usuario user = Usuario.builder().nome("teste").email("teste@gmail.com").senha("senha").build();
		return user;
	}

}
