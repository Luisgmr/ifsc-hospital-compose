package com.luisgmr.ifsc.hospital.model;

import java.time.LocalDate;

public class Lote {

	private long id;
	private String descricao;
	private LocalDate dataFabricacao;
	private LocalDate dataValidade;
	private String status;
	
	
	public Lote() {
		super();
	}

	public Lote(long id, String descricao, LocalDate dataFabricacao, LocalDate dataValidade, String status) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataFabricacao = dataFabricacao;
		this.dataValidade = dataValidade;
		this.status = status;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(LocalDate dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

	public LocalDate getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}