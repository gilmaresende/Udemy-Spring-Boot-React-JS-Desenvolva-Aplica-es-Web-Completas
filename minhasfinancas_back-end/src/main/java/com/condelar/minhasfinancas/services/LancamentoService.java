package com.condelar.minhasfinancas.services;

import java.util.List;
import java.util.Optional;

import com.condelar.minhasfinancas.model.entity.Lancamento;
import com.condelar.minhasfinancas.model.enums.StatusLancamento;

public interface LancamentoService {

	Lancamento salvar(Lancamento lancamento);

	Lancamento atualizar(Lancamento lancamento);

	void deletar(Lancamento lancamento);

	List<Lancamento> buscar(Lancamento lancamentoFiltro);
	
	void atualizarStatus(Lancamento lancamento, StatusLancamento status);

	void validar(Lancamento lancamento);

	Optional<Lancamento> findById(Long id);

}
