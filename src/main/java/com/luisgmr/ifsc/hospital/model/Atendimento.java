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
@Table(name = "atendimento")
public class Atendimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;

	public LocalDateTime dataHoraAtendimento;
	public String pressao;
	public String temperatura;
	public String bpm;
	public String oximetria;
	public String historicoDeDoencas;
	public String alergias;
	public String medicacoesEmUso;
	public String anamnese;
	public String tipoAtendimento;
	public String classificacao;
	public String observacoes;
	public String status;

	@ManyToOne
	@JoinColumn(name = "paciente_id")
	public Paciente paciente;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	public Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "enfermeiro_id")
	public Enfermeiro enfermeiro;

}
