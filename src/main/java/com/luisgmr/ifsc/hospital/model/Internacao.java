package com.luisgmr.ifsc.hospital.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Internacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private LocalDateTime dataHoraInternacao;
	private LocalDateTime dataHoraAlta;
	private String observacao;
	private String status;

	@OneToOne
	@JoinColumn(name = "consulta_id", unique = true)
	private Consulta consulta;

	
}
