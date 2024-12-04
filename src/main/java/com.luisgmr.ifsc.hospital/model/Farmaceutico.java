package com.luisgmr.ifsc.hospital.model;

public class Farmaceutico extends Pessoa {
	
	private String cfr;
	private String senha;
	private String login;
	private String nomeSocial;

	public Farmaceutico(long id, String nome, String fone1, String fone2, String email, String cpfCnpj, String rgInscricaoEstadual, String dataCadastro, String endereco, String cep, String cidade, String bairro, String logradouro, String complemento, String cfr, String senha, String login, String nomeSocial) {
		super(id, nome, fone1, fone2, email, cpfCnpj, rgInscricaoEstadual, dataCadastro, endereco, cep, cidade, bairro, logradouro, complemento);
		this.cfr = cfr;
		this.senha = senha;
		this.login = login;
		this.nomeSocial = nomeSocial;
	}

	public String getCfr() {
		return cfr;
	}

	public void setCfr(String cfr) {
		this.cfr = cfr;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNomeSocial() {
		return nomeSocial;
	}

	public void setNomeSocial(String nomeSocial) {
		this.nomeSocial = nomeSocial;
	}
	
}
