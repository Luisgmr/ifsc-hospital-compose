package com.luisgmr.ifsc.hospital.model;


import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

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
public class Usuario extends Pessoa {

	private String login;
	private String senha;
	private String nomeSocial;

}
