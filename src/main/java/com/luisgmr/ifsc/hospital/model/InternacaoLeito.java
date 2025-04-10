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

public class InternacaoLeito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private LocalDateTime dataHoraAlocacao;
	private LocalDateTime dataHoraDesocupacao;
	private String status;
	@ManyToOne
	@JoinColumn(name = "internacao_id")
	private Internacao internacao;

	@ManyToOne
	@JoinColumn(name = "leito_id")
	private Leito leito;

	@ManyToOne
	@JoinColumn(name = "acompanhante_id")
	private Acompanhante acompanhante;

}
