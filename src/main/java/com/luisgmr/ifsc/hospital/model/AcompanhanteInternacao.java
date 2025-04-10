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
@Table(name = "acompanhante_internacao")
public class AcompanhanteInternacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;

	public LocalDateTime dataEntrada;
	public LocalDateTime dataSaida;
	public String observacao;
	public String status;
	

}
