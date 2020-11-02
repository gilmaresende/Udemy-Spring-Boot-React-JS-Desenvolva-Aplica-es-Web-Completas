package com.condelar.minhasfinancas.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.condelar.minhasfinancas.exception.ErroAutenticacao;
import com.condelar.minhasfinancas.exception.RegraNegocioException;
import com.condelar.minhasfinancas.model.entity.Usuario;
import com.condelar.minhasfinancas.model.repository.UsuarioRepository;
import com.condelar.minhasfinancas.services.impl.UsuarioServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

	@SpyBean
	UsuarioServiceImpl service;

	@MockBean
	UsuarioRepository repository;

	@Test
	public void deveSalvarUmUsuario() {

		Mockito.doNothing().when(service).validarEmail(Mockito.anyString());
		Usuario usuario = Usuario.builder().id(1l).nome("nome").email("email").senha("senha").build();

		Mockito.when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuario);

		Usuario usuarioSalvo = service.salvarUsuario(new Usuario());

		Assertions.assertThat(usuarioSalvo.getId()).isEqualTo(1l);
		Assertions.assertThat(usuarioSalvo.getNome()).isEqualTo("nome");
		Assertions.assertThat(usuarioSalvo.getEmail()).isEqualTo("email");
		Assertions.assertThat(usuarioSalvo.getSenha()).isEqualTo("senha");

	}

	@Test
	public void naoDeveSalvarUmUsuarioComEmailJaCadastrado() {
		String email = "email@email.com";
		Usuario usuario = Usuario.builder().email(email).build();
		Mockito.doThrow(RegraNegocioException.class).when(service).validarEmail(email);

		Assertions.assertThatExceptionOfType(RegraNegocioException.class)
				.isThrownBy(() -> service.salvarUsuario(usuario));

		Mockito.verify(repository, Mockito.never()).save(usuario);
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
