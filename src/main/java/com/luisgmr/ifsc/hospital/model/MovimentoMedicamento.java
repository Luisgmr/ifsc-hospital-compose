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
public class MovimentoMedicamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private LocalDateTime dataHoraMovimento;
	private String tipoMovimento;
	private float qtdMedicamento;
	private String observacao;
	private String status;

	@ManyToOne
	@JoinColumn(name = "lote_id")
	private Lote lote;

	@ManyToOne
	@JoinColumn(name = "laboratorio_id")
	private Laboratorio laboratorio;

	@ManyToOne
	@JoinColumn(name = "receita_id")
	private ReceitaMedicamento receitaMedicamento;

	@ManyToOne
	@JoinColumn(name = "prontuario_id")
	private Prontuario prontuario;

}