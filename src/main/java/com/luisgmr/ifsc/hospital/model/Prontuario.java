package com.luisgmr.ifsc.hospital.model;
import java.time.LocalDateTime;

public class Prontuario {

	private long id;
	private LocalDateTime dataHoraVisita;
	private String descricaoVista;
	private String observacao;
	private String status;
	private InternacaoLeito internacaoLeito;
	private Enfermeiro enfermeiro;
	
	public Prontuario(long id, LocalDateTime dataHoraVisita, String descricaoVista, String observacao,
			String status, InternacaoLeito internacaoLeito, Enfermeiro enfermeiro) {
		super();
		this.id = id;
		this.dataHoraVisita = dataHoraVisita;
		this.descricaoVista = descricaoVista;
		this.observacao = observacao;
		this.status = status;
		this.internacaoLeito = internacaoLeito;
		this.enfermeiro = enfermeiro;
	}
	
	public Prontuario() {
		super();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDateTime getDataHoraVisita() {
		return dataHoraVisita;
	}
	public void setDataHoraVisita(LocalDateTime dataHoraVisita) {
		this.dataHoraVisita = dataHoraVisita;
	}
	public String getDescricaoVista() {
		return descricaoVista;
	}
	public void setDescricaoVista(String descricaoVista) {
		this.descricaoVista = descricaoVista;
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
	public InternacaoLeito getInternacaoLeito() {
		return internacaoLeito;
	}
	public void setInternacaoLeito(InternacaoLeito internacaoLeito) {
		this.internacaoLeito = internacaoLeito;
	}

	public Enfermeiro getEnfermeiro() {
		return enfermeiro;
	}

	public void setEnfermeiro(Enfermeiro enfermeiro) {
		this.enfermeiro = enfermeiro;
	}

}
