package com.luisgmr.ifsc.hospital.model;

import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String responsavel;
	private LocalDateTime dataHoraConsulta;
	private String anamnese;
	private String diagnostico;
	private String prescricao;
	private String observacao;
	private String status;
	@ManyToOne
	@JoinColumn(name = "medico_id")
	private Medico medico;

	@ManyToOne
	@JoinColumn(name = "atendimento_id")
	private Atendimento atendimento;

	@OneToOne
	@JoinColumn(name = "receita_id")
	private Receita receita;

	@OneToOne(mappedBy = "consulta", cascade = CascadeType.ALL)
	private Internacao internacao;

}
