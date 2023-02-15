package com.condelar.helpdesk.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.condelar.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}
