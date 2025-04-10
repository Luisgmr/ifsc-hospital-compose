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
@Table(name = "consulta_exame")
public class ConsultaExame {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	public LocalDateTime dataHoraExame;
	public String analiseExame;
	public String imagemExame;
	public String status;
	@ManyToOne
	@JoinColumn(name = "consulta_id")
	public Consulta consulta;

	@ManyToOne
	@JoinColumn(name = "exame_id")
	public Exame exame;

	
}
