package com.luisgmr.ifsc.hospital.model;

import org.jetbrains.annotations.Nullable;

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
@Table(name = "consulta")
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;

	public String responsavel;
	public LocalDateTime dataHoraConsulta;
	public String anamnese;
	public String diagnostico;
	public String prescricao;
	public String observacao;
	public String status;
	@ManyToOne
	@JoinColumn(name = "medico_id")
	public Medico medico;

	@ManyToOne
	@JoinColumn(name = "atendimento_id")
	public Atendimento atendimento;

	@OneToOne
	@JoinColumn(name = "receita_id")
	public Receita receita;

	@OneToOne(mappedBy = "consulta", cascade = CascadeType.ALL)
	public Internacao internacao;

}
