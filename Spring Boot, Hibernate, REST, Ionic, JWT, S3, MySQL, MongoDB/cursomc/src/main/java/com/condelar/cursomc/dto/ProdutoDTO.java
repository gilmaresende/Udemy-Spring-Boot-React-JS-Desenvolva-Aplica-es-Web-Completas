package com.condelar.cursomc.dto;

import com.condelar.cursomc.domain.Produto;

import java.io.Serializable;

public class ProdutoDTO implements Serializable {

    private Integer id;
    private Double preco;
    private String nome;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto m) {
        setId(m.getId());
        setNome(m.getNome());
        setPreco(m.getPreco());
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
