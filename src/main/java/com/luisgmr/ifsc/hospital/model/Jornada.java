package com.luisgmr.ifsc.hospital.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Jornada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDate dataInicial;
	private Integer cargaHoraria;
	@ManyToOne
	@JoinColumn(name = "medico_id")
	private Medico medico;

	@ManyToOne
	@JoinColumn(name = "enfermeiro_id")
	private Enfermeiro enfermeiro;

	@ManyToOne
	@JoinColumn(name = "farmaceutico_id")
	private Farmaceutico farmaceutico;
	
}
