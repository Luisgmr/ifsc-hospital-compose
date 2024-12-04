package com.luisgmr.ifsc.hospital.model;
import java.time.LocalDateTime;

public class AcompanhanteInternacao {

	private long id;
	private LocalDateTime dataEntrada;
	private LocalDateTime dataSaida;
	private String observacao;
	private String status;
	
	public AcompanhanteInternacao(long id, LocalDateTime dataEntrada, LocalDateTime dataSaida, String observacao,
			String status) {
		super();
		this.id = id;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.observacao = observacao;
		this.status = status;
	}
	
	public AcompanhanteInternacao() {

	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public LocalDateTime getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(LocalDateTime dataSaida) {
		this.dataSaida = dataSaida;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
