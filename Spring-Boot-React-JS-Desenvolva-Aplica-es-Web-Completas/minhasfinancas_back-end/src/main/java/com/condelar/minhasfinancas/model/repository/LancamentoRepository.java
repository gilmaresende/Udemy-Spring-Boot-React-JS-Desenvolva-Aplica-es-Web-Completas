package com.condelar.minhasfinancas.model.repository;

import com.condelar.minhasfinancas.model.entity.Lancamento;
import com.condelar.minhasfinancas.model.enums.StatusLancamento;
import com.condelar.minhasfinancas.model.enums.TipoLancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    @Query(value = "select sum(l.valor) \n" +
            "from Lancamento l\n" +
            "join l.usuario u\n" +
            "where (u.id = :idUsuario)\n" +
            "and (l.tipo = :tipo)\n" +
            "and (l.status = :status)\n" +
            "group by u")
    BigDecimal consultarSaldoPorUsuarioTipoLancamentoEStatus(
            @Param("idUsuario") Long idUsuario,
            @Param("tipo") TipoLancamento tipo,
            @Param("status") StatusLancamento status);

}
