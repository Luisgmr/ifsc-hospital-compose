package com.luisgmr.ifsc.hospital.model;
public class Usuario extends Pessoa {
	
	private String login;
	private String senha;
	private String nomeSocial;

	public Usuario() {
		super();
	}

	public Usuario(String nome, String fone1, String fone2, String email, String cpfCnpj, String rgInscricaoEstadual, String dataCadastro, String endereco, String cep, String cidade, String uf, String bairro, String logradouro, String complemento, String login, String senha, String nomeSocial) {
		super(0, nome, fone1, fone2, email, cpfCnpj, rgInscricaoEstadual, dataCadastro, endereco, cep, cidade, uf, bairro, logradouro, complemento);
		this.login = login;
		this.senha = senha;
		this.nomeSocial = nomeSocial;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNomeSocial() {
		return nomeSocial;
	}

	public void setNomeSocial(String nomeSocial) {
		this.nomeSocial = nomeSocial;
	}
}
