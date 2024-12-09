package com.luisgmr.ifsc.hospital.model;
public class Exame {

	private long id;
	private String tituloExame;
	private String tipoExame;
	private String status;
	
	public Exame() {
	}
	
	public Exame(String tituloExame, String tipoExame, String status) {
		super();
		this.tituloExame = tituloExame;
		this.tipoExame = tipoExame;
		this.status = status;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTituloExame() {
		return tituloExame;
	}
	public void setTituloExame(String tituloExame) {
		this.tituloExame = tituloExame;
	}
	public String getTipoExame() {
		return tipoExame;
	}
	public void setTipoExame(String tipoExame) {
		this.tipoExame = tipoExame;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
