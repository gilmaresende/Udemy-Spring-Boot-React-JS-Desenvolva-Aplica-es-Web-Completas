package com.condelar.cursomc.dto;

import com.condelar.cursomc.domain.Cidade;

public class CidadeDTO {

    private Integer id;
    private String nome;

    public CidadeDTO() {

    }

    public CidadeDTO(Cidade e) {
        this.id = e.getId();
        this.nome = e.getNome();
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
