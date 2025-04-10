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
@Table(name = "internacao_leito")
public class InternacaoLeito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;

	public LocalDateTime dataHoraAlocacao;
	public LocalDateTime dataHoraDesocupacao;
	public String status;
	@ManyToOne
	@JoinColumn(name = "internacao_id")
	public Internacao internacao;

	@ManyToOne
	@JoinColumn(name = "leito_id")
	public Leito leito;

	@ManyToOne
	@JoinColumn(name = "acompanhante_id")
	public Acompanhante acompanhante;

}
