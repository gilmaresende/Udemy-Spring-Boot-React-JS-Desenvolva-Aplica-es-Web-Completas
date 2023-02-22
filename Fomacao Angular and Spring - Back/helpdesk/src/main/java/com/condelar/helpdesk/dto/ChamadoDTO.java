package com.condelar.helpdesk.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.condelar.helpdesk.domain.Chamado;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ChamadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAbertura = LocalDate.now();

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFechamento;

	@NotNull(message = "Campo PRIORIDADE é requerido")
	private Integer prioridade;

	@NotNull(message = "Campo STATUS é requerido")
	private Integer status;

	@NotEmpty(message = "Campo TÍTULO é requerido")
	private String titulo;

	@NotEmpty(message = "Campo OBSERVAÇÕES é requerido")
	private String observacoes;

	@NotNull(message = "Campo TECNICO é requerido")
	private Integer tecnico;

	@NotNull(message = "Campo CLIENTE é requerido")
	private Integer cliente;
	private String nomeTecnico;
	private String nomeCliente;

	public ChamadoDTO() {
		super();
	}

	public ChamadoDTO(Chamado ob) {
		super();
		this.id = ob.getId();
		this.dataAbertura = ob.getDataAbertura();
		this.dataFechamento = ob.getDataFechamento();
		this.prioridade = ob.getPrioridade().getCodigo();
		this.status = ob.getStatus().getCodigo();
		this.titulo = ob.getTitulo();
		this.observacoes = ob.getObservacoes();
		this.tecnico = ob.getTecnico().getId();
		this.cliente = ob.getCliente().getId();
		this.nomeTecnico = ob.getTecnico().getNome();
		this.nomeCliente = ob.getCliente().getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDate getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Integer getTecnico() {
		return tecnico;
	}

	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public String getNomeTecnico() {
		return nomeTecnico;
	}

	public void setNomeTecnico(String nomeTecnico) {
		this.nomeTecnico = nomeTecnico;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

}
