package com.luisgmr.ifsc.hospital.model;
import java.time.LocalDateTime;

public class InternacaoLeito {

	private long id;
	private LocalDateTime dataHoraAlocacao;
	private LocalDateTime dataHoraDesocupacao;
	private String status;
	private Internacao internacao;
	private Leito leito;
	private Acompanhante acompanhante;
	
	public InternacaoLeito(long id, LocalDateTime dataHoraAlocacao, LocalDateTime dataHoraDesocupacao,
			String status, Internacao internacao, Leito leito, Acompanhante acompanhante) {
		super();
		this.id = id;
		this.dataHoraAlocacao = dataHoraAlocacao;
		this.dataHoraDesocupacao = dataHoraDesocupacao;
		this.status = status;
		this.internacao = internacao;
		this.leito = leito;
		this.acompanhante = acompanhante;
	}
	
	public InternacaoLeito() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getDataHoraAlocacao() {
		return dataHoraAlocacao;
	}

	public void setDataHoraAlocacao(LocalDateTime dataHoraAlocacao) {
		this.dataHoraAlocacao = dataHoraAlocacao;
	}

	public LocalDateTime getDataHoraDesocupacao() {
		return dataHoraDesocupacao;
	}

	public void setDataHoraDesocupacao(LocalDateTime dataHoraDesocupacao) {
		this.dataHoraDesocupacao = dataHoraDesocupacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Internacao getInternacao() {
		return internacao;
	}

	public void setInternacao(Internacao internacao) {
		this.internacao = internacao;
	}

	public Leito getLeito() {
		return leito;
	}

	public void setLeito(Leito leito) {
		this.leito = leito;
	}

	public Acompanhante getAcompanhante() {
		return acompanhante;
	}

	public void setAcompanhante(Acompanhante acompanhante) {
		this.acompanhante = acompanhante;
	}
	
}
