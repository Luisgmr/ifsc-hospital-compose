package com.luisgmr.ifsc.hospital.model;

import java.time.LocalDateTime;

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
public class ConsultaExame {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDateTime dataHoraExame;
	private String analiseExame;
	private String imagemExame;
	private String status;
	@ManyToOne
	@JoinColumn(name = "consulta_id")
	private Consulta consulta;

	@ManyToOne
	@JoinColumn(name = "exame_id")
	private Exame exame;

	
}
