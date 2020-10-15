package com.condelar.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.condelar.minhasfinancas.model.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
