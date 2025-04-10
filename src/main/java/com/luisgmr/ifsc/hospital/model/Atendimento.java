package com.luisgmr.ifsc.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Atendimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private LocalDateTime dataHoraAtendimento;
	private String pressao;
	private String temperatura;
	private String bpm;
	private String oximetria;
	private String historicoDeDoencas;
	private String alergias;
	private String medicacoesEmUso;
	private String anamnese;
	private String tipoAtendimento;
	private String classificacao;
	private String observacoes;
	private String status;

	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "enfermeiro_id")
	private Enfermeiro enfermeiro;

}
