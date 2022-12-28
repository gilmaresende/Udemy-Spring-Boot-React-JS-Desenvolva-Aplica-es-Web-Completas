package com.condelar.minhasfinancas.services.impl;

import com.condelar.minhasfinancas.exception.RegraNegocioException;
import com.condelar.minhasfinancas.model.entity.Lancamento;
import com.condelar.minhasfinancas.model.enums.StatusLancamento;
import com.condelar.minhasfinancas.model.enums.TipoLancamento;
import com.condelar.minhasfinancas.model.repository.LancamentoRepository;
import com.condelar.minhasfinancas.services.LancamentoService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LancamentoServiceImpl implements LancamentoService {

    private final LancamentoRepository repository;

    public LancamentoServiceImpl(LancamentoRepository respository) {
        this.repository = respository;
    }

    @Override
    @Transactional
    public Lancamento salvar(Lancamento lancamento) {
        validar(lancamento);
        lancamento.setStatus(StatusLancamento.PENDENTE);
        return repository.save(lancamento);
    }

    @Override
    @Transactional
    public Lancamento atualizar(Lancamento lancamento) {
        Objects.requireNonNull(lancamento.getId());
        validar(lancamento);
        return repository.save(lancamento);
    }

    @Override
    @Transactional
    public void deletar(Lancamento lancamento) {
        Objects.requireNonNull(lancamento.getId());
        repository.delete(lancamento);
    }

    @Override
    @Transactional
    public List<Lancamento> buscar(Lancamento lancamentoFiltro) {
        Example example = Example.of(lancamentoFiltro, ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        return repository.findAll(example);
    }

    @Override
    public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {
        lancamento.setStatus(status);
        atualizar(lancamento);
    }

    @Override
    public void validar(Lancamento lancamento) {

        if (lancamento.getDescricao() == null || lancamento.getDescricao().trim().equals("")) {
            throw new RegraNegocioException("Informa uma Descrição valida!!");
        }

        if (lancamento.getMes() == null || lancamento.getMes() < 1 || lancamento.getMes() > 12) {
            throw new RegraNegocioException("Informe um mês Valido!!");
        }

        if (lancamento.getAno() == null || lancamento.getAno().toString().length() != 4) {
            throw new RegraNegocioException("Informe um ano Valido!!");
        }

        if (lancamento.getUsuario() == null || lancamento.getUsuario().getId() == null) {
            throw new RegraNegocioException("Informe um Usuario!!");
        }

        if (lancamento.getValor() == null || lancamento.getValor().compareTo(BigDecimal.ZERO) < 1) {
            throw new RegraNegocioException("Valor do Lancamento Invalido!!");
        }

        if (lancamento.getTipo() == null) {
            throw new RegraNegocioException("Informe o Tipo do Lancamento!!");
        }
    }

    @Override
    public Optional<Lancamento> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public BigDecimal consultarSaldoPorUsuario(Long id) {
        BigDecimal receitas = repository.consultarSaldoPorUsuarioTipoLancamentoEStatus(id, TipoLancamento.RECEITA, StatusLancamento.EFETIVADO);
        BigDecimal despesas = repository.consultarSaldoPorUsuarioTipoLancamentoEStatus(id, TipoLancamento.DESPESA, StatusLancamento.EFETIVADO);
        if (receitas == null) {
            receitas = BigDecimal.ZERO;
        }
        if (despesas == null) {
            despesas = BigDecimal.ZERO;
        }
        return receitas.subtract(despesas);
    }
}
