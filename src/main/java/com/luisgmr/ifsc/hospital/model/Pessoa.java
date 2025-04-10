package com.luisgmr.ifsc.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String fone1;
	private String fone2;
	private String email;
	private String cpfCnpj;
	private String rgInscricaoEstadual;
	private String dataCadastro;
	private String endereco;
	private String cep;
	private String cidade;
	private String uf;
	private String bairro;
	private String logradouro;
	private String complemento;

	@Override
	public String toString() {
		return "Pessoa(id=$id | nome=$nome)".replace("$id", String.valueOf(id)).replace("$nome", nome);
	}
}
