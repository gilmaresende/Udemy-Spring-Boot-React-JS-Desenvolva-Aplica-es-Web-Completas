package com.condelar.cursomc.repositories;

import com.condelar.cursomc.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

    @Query("select obj from Cidade obj where obj.estado.id = :estadoId order by obj.nome")
    public List<Cidade> findCidades(@Param("estadoId") Integer estadoId);
}
