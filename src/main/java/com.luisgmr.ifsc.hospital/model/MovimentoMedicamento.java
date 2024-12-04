package com.luisgmr.ifsc.hospital.model;

import java.time.LocalDateTime;

public class MovimentoMedicamento {

	private long id;
	private LocalDateTime dataHoraMovimento;
	private String tipoMovimento;
	private float qtdMedicamento;
	private String observacao;
	private String status;
	private Lote lote;
	private Laboratorio laboratorio;
	private ReceitaMedicamento receitaMedicamento;
	private Prontuario prontuario;
	
	public MovimentoMedicamento() {
	}

	public MovimentoMedicamento(long id, LocalDateTime dataHoraMovimento, String tipoMovimento, float qtdMedicamento,
			String observacao, String status, Lote lote, Laboratorio laboratorio,
			ReceitaMedicamento receitaMedicamento, Prontuario prontuario) {
		super();
		this.id = id;
		this.dataHoraMovimento = dataHoraMovimento;
		this.tipoMovimento = tipoMovimento;
		this.qtdMedicamento = qtdMedicamento;
		this.observacao = observacao;
		this.status = status;
		this.lote = lote;
		this.laboratorio = laboratorio;
		this.receitaMedicamento = receitaMedicamento;
		this.prontuario = prontuario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getDataHoraMovimento() {
		return dataHoraMovimento;
	}

	public void setDataHoraMovimento(LocalDateTime dataHoraMovimento) {
		this.dataHoraMovimento = dataHoraMovimento;
	}

	public String getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(String tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

	public float getQtdMedicamento() {
		return qtdMedicamento;
	}

	public void setQtdMedicamento(float qtdMedicamento) {
		this.qtdMedicamento = qtdMedicamento;
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

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public Laboratorio getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}

	public ReceitaMedicamento getReceitaMedicamento() {
		return receitaMedicamento;
	}

	public void setReceitaMedicamento(ReceitaMedicamento receitaMedicamento) {
		this.receitaMedicamento = receitaMedicamento;
	}

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}
}