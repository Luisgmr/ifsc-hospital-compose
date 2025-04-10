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
@Table(name = "internacao")
public class Internacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;

	public LocalDateTime dataHoraInternacao;
	public LocalDateTime dataHoraAlta;
	public String observacao;
	public String status;

	@OneToOne
	@JoinColumn(name = "consulta_id", unique = true)
	public Consulta consulta;

	
}
