package com.luisgmr.ifsc.hospital.model;

import java.time.LocalDate;

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
@Table(name = "jornada")
public class Jornada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	public LocalDate dataInicial;
	public Integer cargaHoraria;
	@ManyToOne
	@JoinColumn(name = "medico_id")
	public Medico medico;

	@ManyToOne
	@JoinColumn(name = "enfermeiro_id")
	public Enfermeiro enfermeiro;

	@ManyToOne
	@JoinColumn(name = "farmaceutico_id")
	public Farmaceutico farmaceutico;
	
}
