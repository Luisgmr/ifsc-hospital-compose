package com.luisgmr.ifsc.hospital.model;

import java.time.LocalDateTime;

public class Atendimento {

	private long id;
	private LocalDateTime dataHoraAtendimento;
	private String pressao;
	private String temperatura;
	private String bpm;
	private String oximetria;
	private String historicoDeDoencas;
	private String alergias;
	private String medicacoesEmUso;
	private String anamnese;
	private String tipoAtendimento;
	private String classificacao;
	private String observacoes;
	private String status;
	private Paciente paciente;
	private Usuario usuario;
	private Enfermeiro enfermeiro;
	
	public Atendimento() {
	}

	public Atendimento(LocalDateTime dataHoraAtendimento, String pressao, String temperatura, String bpm,
			String oximetria, String historicoDeDoencas, String alergias, String medicacoesEmUso, String anamnese,
			String tipoAtendimento, String classificacao, String observacoes, String status, Paciente paciente,
			Usuario usuario, Enfermeiro enfermeiro) {
		super();
		this.dataHoraAtendimento = dataHoraAtendimento;
		this.pressao = pressao;
		this.temperatura = temperatura;
		this.bpm = bpm;
		this.oximetria = oximetria;
		this.historicoDeDoencas = historicoDeDoencas;
		this.alergias = alergias;
		this.medicacoesEmUso = medicacoesEmUso;
		this.anamnese = anamnese;
		this.tipoAtendimento = tipoAtendimento;
		this.classificacao = classificacao;
		this.observacoes = observacoes;
		this.status = status;
		this.paciente = paciente;
		this.usuario = usuario;
		this.enfermeiro = enfermeiro;
	}


	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public LocalDateTime getDataHoraAtendimento() {
		return dataHoraAtendimento;
	}



	public void setDataHoraAtendimento(LocalDateTime dataHoraAtendimento) {
		this.dataHoraAtendimento = dataHoraAtendimento;
	}



	public String getPressao() {
		return pressao;
	}



	public void setPressao(String pressao) {
		this.pressao = pressao;
	}



	public String getTemperatura() {
		return temperatura;
	}



	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}



	public String getBpm() {
		return bpm;
	}



	public void setBpm(String bpm) {
		this.bpm = bpm;
	}



	public String getOximetria() {
		return oximetria;
	}



	public void setOximetria(String oximetria) {
		this.oximetria = oximetria;
	}



	public String getHistoricoDeDoencas() {
		return historicoDeDoencas;
	}



	public void setHistoricoDeDoencas(String historicoDeDoencas) {
		this.historicoDeDoencas = historicoDeDoencas;
	}



	public String getAlergias() {
		return alergias;
	}



	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}



	public String getMedicacoesEmUso() {
		return medicacoesEmUso;
	}



	public void setMedicacoesEmUso(String medicacoesEmUso) {
		this.medicacoesEmUso = medicacoesEmUso;
	}



	public String getAnamnese() {
		return anamnese;
	}



	public void setAnamnese(String anamnese) {
		this.anamnese = anamnese;
	}



	public String getTipoAtendimento() {
		return tipoAtendimento;
	}



	public void setTipoAtendimento(String tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}



	public String getClassificacao() {
		return classificacao;
	}



	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}



	public String getObservacoes() {
		return observacoes;
	}



	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}

	public Paciente getPaciente() {
		return paciente;
	}


	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Enfermeiro getEnfermeiro() {
		return enfermeiro;
	}


	public void setEnfermeiro(Enfermeiro enfermeiro) {
		this.enfermeiro = enfermeiro;
	}
}
