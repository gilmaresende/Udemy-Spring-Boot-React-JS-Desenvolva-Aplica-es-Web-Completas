package com.condelar.minhasfinancas.services;

import com.condelar.minhasfinancas.exception.RegraNegocioException;
import com.condelar.minhasfinancas.model.entity.Lancamento;
import com.condelar.minhasfinancas.model.entity.Usuario;
import com.condelar.minhasfinancas.model.enums.StatusLancamento;
import com.condelar.minhasfinancas.model.enums.TipoLancamento;
import com.condelar.minhasfinancas.model.repository.LancamentoRepository;
import com.condelar.minhasfinancas.model.repository.LancamentoRepositoryTest;
import com.condelar.minhasfinancas.services.impl.LancamentoServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Example;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class LancamentoServiceTest {

    @SpyBean
    LancamentoServiceImpl service;

    @MockBean
    LancamentoRepository repository;

    @Test
    public void deveSalvarUmLancamento() {
        //cenario
        Lancamento lancamentoASalvar = LancamentoRepositoryTest.newLancamento();
        Mockito.doNothing().when(service).validar(lancamentoASalvar);

        Lancamento lancamentoSalvo = LancamentoRepositoryTest.newLancamento();
        lancamentoSalvo.setId(1l);
        lancamentoSalvo.setStatus(StatusLancamento.PENDENTE);
        Mockito.when(repository.save(lancamentoASalvar)).thenReturn(lancamentoSalvo);

        //execução
        Lancamento lancamento = service.salvar(lancamentoASalvar);

        //verificação
        Assertions.assertThat(lancamento.getId()).isEqualTo(lancamentoSalvo.getId());
        Assertions.assertThat(lancamento.getStatus()).isEqualTo(StatusLancamento.PENDENTE);

    }

    @Test
    public void naoDeveSalvarUmLancamentoQuandoHouverErroDeValidacao() {
        //cenario
        Lancamento lancamentoASalvar = LancamentoRepositoryTest.newLancamento();
        Mockito.doThrow(RegraNegocioException.class).when(service).validar(lancamentoASalvar);

        //execução
        Assertions.assertThatExceptionOfType(RegraNegocioException.class)
                .isThrownBy(() -> service.salvar(lancamentoASalvar));

        //verificacao
        Mockito.verify(repository, Mockito.never()).save(lancamentoASalvar);

    }

    @Test
    public void deveAtualizarUmLancamento() {
        //cenario
        Lancamento lancamentoSalvo = LancamentoRepositoryTest.newLancamento();
        lancamentoSalvo.setId(1l);
        lancamentoSalvo.setStatus(StatusLancamento.PENDENTE);

        Mockito.doNothing().when(service).validar(lancamentoSalvo);

        Mockito.when(repository.save(lancamentoSalvo)).thenReturn(lancamentoSalvo);

        //execução
        service.atualizar(lancamentoSalvo);

        //verificação
        Mockito.verify(repository, Mockito.times(1)).save(lancamentoSalvo);
    }

    @Test
    public void deveLancarAoTentarSalvarUmLancamentoQueNaoFoiSalvo() {
        //cenario
        Lancamento lancamentoASalvar = LancamentoRepositoryTest.newLancamento();

        //execução
        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> service.atualizar(lancamentoASalvar));

        //verificacao
        Mockito.verify(repository, Mockito.never()).save(lancamentoASalvar);

    }

    @Test
    public void deveDeletarLancamento() {
        //cenario
        Lancamento lancamento = LancamentoRepositoryTest.newLancamento();
        lancamento.setId(1l);

        //execucao
        service.deletar(lancamento);

        //verificacao
        Mockito.verify(repository).delete(lancamento);
    }

    @Test
    public void deveLancarErroAoTentarDeletarLancamentoNaoSalvo() {
        //cenario
        Lancamento lancamento = LancamentoRepositoryTest.newLancamento();

        //execucao

        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> service.deletar(lancamento));

        //verificacao
        Mockito.verify(repository, Mockito.never()).delete(lancamento);
    }

    @Test
    public void deveFiltrarLancamento() {
        //cenario
        Lancamento lancamento = LancamentoRepositoryTest.newLancamento();
        lancamento.setId(1l);

        List<Lancamento> lista = Arrays.asList(lancamento);
        Mockito.when(repository.findAll(Mockito.any(Example.class))).thenReturn(lista);

        //execucao
        List<Lancamento> resultado = service.buscar(lancamento);

        //verificacao
        Assertions.assertThat(resultado).isNotEmpty().hasSize(1).contains(lancamento);
    }

    @Test
    void deveAtualizarstatus() {

        //cenario
        Lancamento lancamento = LancamentoRepositoryTest.newLancamento();
        lancamento.setId(1l);
        lancamento.setStatus(StatusLancamento.PENDENTE);


        Mockito.doReturn(lancamento).when(service).atualizar(lancamento);

        StatusLancamento novoStatus = StatusLancamento.EFETIVADO;

        //execucao
        service.atualizarStatus(lancamento, novoStatus);

        //verificacao
        Assertions.assertThat(lancamento.getStatus()).isEqualTo(novoStatus);
        Mockito.verify(service).atualizar(lancamento);

    }

    @Test
    void deveObterLancamentoPorID() {
        Long id = 1l;

        //cenario
        Lancamento lancamento = LancamentoRepositoryTest.newLancamento();
        lancamento.setId(id);

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(lancamento));

        //execucao
        Optional<Lancamento> resultado = service.findById(id);

        //verificacao
        Assertions.assertThat(resultado.isPresent()).isTrue();
    }

    @Test
    void deveRetornarVazioQuandoLancamentoNaoExiste() {
        Long id = 1l;

        //cenario
        Lancamento lancamento = LancamentoRepositoryTest.newLancamento();
        lancamento.setId(id);

        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        //execucao
        Optional<Lancamento> resultado = service.findById(id);

        //verificacao
        Assertions.assertThat(resultado.isPresent()).isFalse();
    }

    @Test
    void deveLancarErrosAovalidarLancamento() {
        Lancamento lancamento = new Lancamento();

        //mensagem erro Lancamento sem descricao
        Throwable exception = Assertions.catchThrowable(() -> service.validar(lancamento));
        Assertions.assertThat(exception).isInstanceOf(RegraNegocioException.class).hasMessage("Informa uma Descrição valida!!");

        lancamento.setDescricao("");

        //mensagem erro Lancamento com descricao vazia
        exception = Assertions.catchThrowable(() -> service.validar(lancamento));
        Assertions.assertThat(exception).isInstanceOf(RegraNegocioException.class).hasMessage("Informa uma Descrição valida!!");

        lancamento.setDescricao("salario");

        //mensagem erro Lancamento sem mes
        exception = Assertions.catchThrowable(() -> service.validar(lancamento));
        Assertions.assertThat(exception).isInstanceOf(RegraNegocioException.class).hasMessage("Informe um mês Valido!!");

        lancamento.setMes(0);

        //mensagem erro Lancamento com mes menor que 1
        exception = Assertions.catchThrowable(() -> service.validar(lancamento));
        Assertions.assertThat(exception).isInstanceOf(RegraNegocioException.class).hasMessage("Informe um mês Valido!!");

        lancamento.setMes(13);

        //mensagem erro Lancamento com mes maior que 12
        exception = Assertions.catchThrowable(() -> service.validar(lancamento));
        Assertions.assertThat(exception).isInstanceOf(RegraNegocioException.class).hasMessage("Informe um mês Valido!!");

        lancamento.setMes(1);

        //mensagem erro Lancamento sem ano
        exception = Assertions.catchThrowable(() -> service.validar(lancamento));
        Assertions.assertThat(exception).isInstanceOf(RegraNegocioException.class).hasMessage("Informe um ano Valido!!");

        lancamento.setAno(999);

        //mensagem erro Lancamento com ano diferente de 4 digitos
        exception = Assertions.catchThrowable(() -> service.validar(lancamento));
        Assertions.assertThat(exception).isInstanceOf(RegraNegocioException.class).hasMessage("Informe um ano Valido!!");

        lancamento.setAno(2018);

        //mensagem erro lancamento sem usuario
        exception = Assertions.catchThrowable(() -> service.validar(lancamento));
        Assertions.assertThat(exception).isInstanceOf(RegraNegocioException.class).hasMessage("Informe um Usuario!!");

        lancamento.setUsuario(new Usuario());

        //mensagem erro lancamento com usuario sem ID
        exception = Assertions.catchThrowable(() -> service.validar(lancamento));
        Assertions.assertThat(exception).isInstanceOf(RegraNegocioException.class).hasMessage("Informe um Usuario!!");

        lancamento.setUsuario(new Usuario().builder().id(1l).build());

        //mensagem erro de Lancamento sem valor
        exception = Assertions.catchThrowable(() -> service.validar(lancamento));
        Assertions.assertThat(exception).isInstanceOf(RegraNegocioException.class).hasMessage("Valor do Lancamento Invalido!!");

        lancamento.setValor(BigDecimal.valueOf(0));

        //mensagem erro de Lancamento valor menor que 1 centavo
        exception = Assertions.catchThrowable(() -> service.validar(lancamento));
        Assertions.assertThat(exception).isInstanceOf(RegraNegocioException.class).hasMessage("Valor do Lancamento Invalido!!");

        lancamento.setValor(BigDecimal.valueOf(20));

        //mensagem erro de tipo
        exception = Assertions.catchThrowable(() -> service.validar(lancamento));
        Assertions.assertThat(exception).isInstanceOf(RegraNegocioException.class).hasMessage("Informe o Tipo do Lancamento!!");

        lancamento.setTipo(TipoLancamento.RECEITA);

    }
}
