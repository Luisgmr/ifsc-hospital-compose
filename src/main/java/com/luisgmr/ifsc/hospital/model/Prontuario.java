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
@Table(name = "prontuario")
public class Prontuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;

	public LocalDateTime dataHoraVisita;
	public String descricaoVista;
	public String observacao;
	public String status;
	@ManyToOne
	@JoinColumn(name = "internacao_leito_id")
	public InternacaoLeito internacaoLeito;

	@ManyToOne
	@JoinColumn(name = "enfermeiro_id")
	public Enfermeiro enfermeiro;


}
