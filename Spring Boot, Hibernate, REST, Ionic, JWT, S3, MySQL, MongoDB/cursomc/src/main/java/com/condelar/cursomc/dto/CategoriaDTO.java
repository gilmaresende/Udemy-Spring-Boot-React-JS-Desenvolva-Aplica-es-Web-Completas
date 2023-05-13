package com.condelar.cursomc.dto;

import com.condelar.cursomc.domain.Categoria;

import java.io.Serializable;

public class CategoriaDTO implements Serializable {

    private Integer id;

    private String nome;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Categoria m) {
        setId(m.getId());
        setNome(m.getNome());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
