package com.condelar.cursomc.repositories;

import com.condelar.cursomc.domain.Pagamento;
import com.condelar.cursomc.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
