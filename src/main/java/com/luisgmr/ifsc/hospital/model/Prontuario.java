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

public class Prontuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private LocalDateTime dataHoraVisita;
	private String descricaoVista;
	private String observacao;
	private String status;
	@ManyToOne
	@JoinColumn(name = "internacao_leito_id")
	private InternacaoLeito internacaoLeito;

	@ManyToOne
	@JoinColumn(name = "enfermeiro_id")
	private Enfermeiro enfermeiro;


}
