package com.condelar.helpdesk.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.condelar.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
