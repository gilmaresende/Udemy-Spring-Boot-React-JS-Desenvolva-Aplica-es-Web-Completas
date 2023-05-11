package com.condelar.cursomc.repositories;

import com.condelar.cursomc.domain.Cliente;
import com.condelar.cursomc.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
