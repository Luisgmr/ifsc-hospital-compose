package com.luisgmr.ifsc.hospital.model;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Paciente extends Pessoa {
 
	private String tipoSanguineo;
	private String sexo;
	private String nomeSocial;
	private LocalDate dataNascimento;

}
