package com.condelar.minhasfinancas.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.condelar.minhasfinancas.exception.ErroAutenticacao;
import com.condelar.minhasfinancas.exception.RegraNegocioException;
import com.condelar.minhasfinancas.model.entity.Usuario;
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

	@Test
	public void deveAutenticarUsuarioComSucesso() {
		String email = "teste@gmail.com";
		String senha = "senha";

		Usuario usuario = Usuario.builder().email(email).senha(senha).build();

		Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));

		Usuario result = service.autenticar(email, senha);

		Assertions.assertThat(result).isNotNull();

	}

	@Test
	public void deveLancarErroQuandoNaoEncontrarUsuarioCadastradoComEmailInfomrado() {
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

		Throwable exception = Assertions.catchThrowable(() -> service.autenticar("teste@teste.com", "123"));

		Assertions.assertThat(exception).isInstanceOf(ErroAutenticacao.class).hasMessage("Usuario não encontrado!");

	}

	@Test
	public void devaLancarErroQuandoSenhaNaoBater() {
		String senha = "senha";
		Usuario usuario = Usuario.builder().email("teste@teste.com").senha(senha).build();

		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));

		Throwable exception = Assertions.catchThrowable(() -> service.autenticar("teste@teste.com", "123"));

		Assertions.assertThat(exception).isInstanceOf(ErroAutenticacao.class).hasMessage("Senha Invalida!");

	}

}
