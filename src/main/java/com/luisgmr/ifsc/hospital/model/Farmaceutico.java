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
@PrimaryKeyJoinColumn(name = "pessoa_id")
@DiscriminatorValue("FARMACEUTICO")
public class Farmaceutico extends Pessoa {
	
	public String cfr;
	public String senha;
	public String login;
	public String nomeSocial;

	
}
