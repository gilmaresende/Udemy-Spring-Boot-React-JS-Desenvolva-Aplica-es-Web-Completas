package com.condelar.cursomc.dto;

import com.condelar.cursomc.domain.Estado;

public class EstadoDTO {

    private Integer id;
    private String nome;

    public EstadoDTO() {

    }

    public EstadoDTO(Estado e) {
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
