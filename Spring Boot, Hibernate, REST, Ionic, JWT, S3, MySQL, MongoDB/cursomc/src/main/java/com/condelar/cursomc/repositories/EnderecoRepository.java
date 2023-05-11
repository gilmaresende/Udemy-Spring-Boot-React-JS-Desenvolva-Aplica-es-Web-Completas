package com.condelar.cursomc.repositories;

import com.condelar.cursomc.domain.Cliente;
import com.condelar.cursomc.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
