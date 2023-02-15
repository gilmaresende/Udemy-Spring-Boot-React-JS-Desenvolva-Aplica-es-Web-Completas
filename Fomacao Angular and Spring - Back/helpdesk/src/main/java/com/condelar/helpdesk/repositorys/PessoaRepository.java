package com.condelar.helpdesk.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.condelar.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}
