package com.luisgmr.ifsc.hospital.model;
public class Usuario extends Pessoa {
	
	private String login;
	private String senha;
	private String nomeSocial;

	public Usuario(long id, String nome, String fone1, String fone2, String email, String cpfCnpj, String rgInscricaoEstadual, String dataCadastro, String endereco, String cep, String cidade, String bairro, String logradouro, String complemento, String login, String senha, String nomeSocial) {
		super(id, nome, fone1, fone2, email, cpfCnpj, rgInscricaoEstadual, dataCadastro, endereco, cep, cidade, bairro, logradouro, complemento);
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
