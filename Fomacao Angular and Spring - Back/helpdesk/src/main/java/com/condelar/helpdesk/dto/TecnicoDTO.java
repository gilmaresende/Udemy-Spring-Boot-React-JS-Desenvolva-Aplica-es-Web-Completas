package com.condelar.helpdesk.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.condelar.helpdesk.domain.Tecnico;
import com.condelar.helpdesk.domain.enuns.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

public class TecnicoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Integer id;
	protected String nome;

	protected String cpf;

	protected String email;
	protected String senha;

	protected Set<Perfil> perfis = new HashSet<>();

	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataCriacao = LocalDate.now();

	public TecnicoDTO() {
		super();
	}

	public TecnicoDTO(Tecnico ob) {
		super();
		this.id = ob.getId();
		this.nome = ob.getNome();
		this.cpf = ob.getCpf();
		this.email = ob.getEmail();
		this.senha = ob.getSenha();
		this.perfis = ob.getPerfis();
		this.dataCriacao = ob.getDataCriacao();
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}
