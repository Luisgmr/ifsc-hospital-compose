package com.luisgmr.ifsc.hospital.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ala")
public class Ala {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;

	public String descricao;
	public String status;

}
