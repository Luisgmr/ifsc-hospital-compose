package com.luisgmr.ifsc.hospital.model;

public class Ala {

	private long id;
	private String descricao;
	private String status;
	
	public Ala(long id, String descricao, String status) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.status = status;
	}

	public Ala() {
		super();
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
