package com.luisgmr.ifsc.hospital.model;

import java.time.LocalDateTime;

public class ConsultaExame {

	private long id;
	private LocalDateTime dataHoraExame;
	private String analiseExame;
	private String imagemExame;
	private String status;
	private Consulta consulta;
	private Exame exame;
	
	public ConsultaExame() {
	}

	public ConsultaExame(long id, LocalDateTime dataHoraExame, String analiseExame, String imagemExame,
			String status, Consulta consulta, Exame exame) {
		this.id = id;
		this.dataHoraExame = dataHoraExame;
		this.analiseExame = analiseExame;
		this.imagemExame = imagemExame;
		this.status = status;
		this.consulta = consulta;
		this.exame = exame;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getDataHoraExame() {
		return dataHoraExame;
	}

	public void setDataHoraExame(LocalDateTime dataHoraExame) {
		this.dataHoraExame = dataHoraExame;
	}

	public String getAnaliseExame() {
		return analiseExame;
	}

	public void setAnaliseExame(String analiseExame) {
		this.analiseExame = analiseExame;
	}

	public String getImagemExame() {
		return imagemExame;
	}

	public void setImagemExame(String imagemExame) {
		this.imagemExame = imagemExame;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}
	
}
