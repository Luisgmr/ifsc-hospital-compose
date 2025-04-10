package com.luisgmr.ifsc.hospital.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Ala {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String descricao;
	private String status;

}
