package com.luisgmr.ifsc.hospital.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("FORNECEDOR")
@PrimaryKeyJoinColumn(name = "pessoa_id")

public class Fornecedor extends Pessoa{

	public Integer nomeFantasia;
	public Integer contato;
	
}
