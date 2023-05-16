package com.condelar.cursomc.repositories;

import com.condelar.cursomc.domain.Categoria;
import com.condelar.cursomc.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Transactional(readOnly = true)
    @Query(value = "SELECT DISTINCT ob \n" +
            "FROM Produto ob \n" +
            "   INNER JOIN ob.categorias cat \n" +
            "WHERE (ob.nome LIKE %:nome%) \n" +
            "AND cat IN (:categorias)")
    Page<Produto> search(@Param("nome") String nome,
                         @Param("categorias") List<Categoria> categorias,
                         Pageable pageRequest);


    @Transactional(readOnly = true)
    Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);
}
