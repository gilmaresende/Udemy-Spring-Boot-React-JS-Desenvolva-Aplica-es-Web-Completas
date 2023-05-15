package com.condelar.cursomc.dto;

import com.condelar.cursomc.domain.Cliente;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class ClienteDTO implements Serializable {

    private Integer id;

    @NotEmpty(message = "O nome não pode esta em Branco")
    @Length(min = 5, max = 120, message = "O Tamenho deve ser entre 5 e 120 caracteres")
    private String nome;

    @NotEmpty(message = "O nome não pode esta em Branco")
    @Email(message = "e-mail invalido")
    private String email;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente ob) {
        setId(ob.getId());
        setNome(ob.getNome());
        setEmail(ob.getEmail());
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
