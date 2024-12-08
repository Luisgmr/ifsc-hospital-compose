package com.luisgmr.ifsc.hospital.model;

import java.time.LocalDate;

public class Paciente extends Pessoa {
 
	private String tipoSanguineo;
	private String sexo;
	private String nomeSocial;
	private LocalDate dataNascimento;

	public Paciente(String nome, String fone1, String fone2, String email, String cpfCnpj, String rgInscricaoEstadual, String dataCadastro, String endereco, String cep, String cidade, String uf, String bairro, String logradouro, String complemento, String tipoSanguineo, String sexo, String nomeSocial, LocalDate dataNascimento) {
		super(0, nome, fone1, fone2, email, cpfCnpj, rgInscricaoEstadual, dataCadastro, endereco, cep, cidade, uf, bairro, logradouro, complemento);
		this.tipoSanguineo = tipoSanguineo;
		this.sexo = sexo;
		this.nomeSocial = nomeSocial;
		this.dataNascimento = dataNascimento;
	}

	public String getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNomeSocial() {
		return nomeSocial;
	}

	public void setNomeSocial(String nomeSocial) {
		this.nomeSocial = nomeSocial;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
}
