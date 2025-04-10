package com.luisgmr.ifsc.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pessoa", length = 31)
@Table(name = "pessoa")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	public String nome;
	public String fone1;
	public String fone2;
	public String email;
	public String cpfCnpj;
	public String rgInscricaoEstadual;
	public String dataCadastro;
	public String endereco;
	public String cep;
	public String cidade;
	public String uf;
	public String bairro;
	public String logradouro;
	public String complemento;

	@Override
	public String toString() {
		return "Pessoa(id=$id | nome=$nome)".replace("$id", String.valueOf(id)).replace("$nome", nome);
	}
}
