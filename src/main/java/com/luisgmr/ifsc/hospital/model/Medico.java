package com.luisgmr.ifsc.hospital.model;

public class Medico extends Pessoa {

	private String crm;
	private String senha;
	private String login;
	private String nomeSocial;

	public Medico(long id, String nome, String fone1, String fone2, String email, String cpfCnpj, String rgInscricaoEstadual, String dataCadastro, String endereco, String cep, String cidade, String bairro, String logradouro, String complemento, String crm, String senha, String login, String nomeSocial) {
		super(id, nome, fone1, fone2, email, cpfCnpj, rgInscricaoEstadual, dataCadastro, endereco, cep, cidade, bairro, logradouro, complemento);
		this.crm = crm;
		this.senha = senha;
		this.login = login;
		this.nomeSocial = nomeSocial;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
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
