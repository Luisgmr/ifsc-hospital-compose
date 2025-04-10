package com.luisgmr.ifsc.hospital.model;

import java.time.LocalDateTime;

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
@Table(name = "movimento_medicamento")
public class MovimentoMedicamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;

	public LocalDateTime dataHoraMovimento;
	public String tipoMovimento;
	public float qtdMedicamento;
	public String observacao;
	public String status;

	@ManyToOne
	@JoinColumn(name = "lote_id")
	public Lote lote;

	@ManyToOne
	@JoinColumn(name = "laboratorio_id")
	public Laboratorio laboratorio;

	@ManyToOne
	@JoinColumn(name = "receita_id")
	public ReceitaMedicamento receitaMedicamento;

	@ManyToOne
	@JoinColumn(name = "prontuario_id")
	public Prontuario prontuario;

}