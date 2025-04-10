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
@DiscriminatorValue("MEDICO")
public class Medico extends Pessoa {

	public String crm;
	public String senha;
	public String login;
	public String nomeSocial;

	
}
