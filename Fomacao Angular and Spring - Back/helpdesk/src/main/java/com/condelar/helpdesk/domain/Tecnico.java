package com.condelar.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.condelar.helpdesk.domain.enuns.Perfil;
import com.condelar.helpdesk.dto.TecnicoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Tecnico extends Pessoa {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToMany(mappedBy = "tecnico")
	private List<Chamado> chamados = new ArrayList<>();

	public Tecnico() {
		super();
		addPerfis(Perfil.TECNICO);
	}

	public Tecnico(TecnicoDTO ob) {
		super();
		this.id = ob.getId();
		this.nome = ob.getNome();
		this.cpf = ob.getCpf();
		this.email = ob.getEmail();
		this.senha = ob.getSenha();
		this.perfis.addAll(ob.getPerfis().stream().map(m -> m.getCodigo()).collect(Collectors.toSet()));
		this.dataCriacao = ob.getDataCriacao();
	}

	public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfis(Perfil.TECNICO);
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
}
