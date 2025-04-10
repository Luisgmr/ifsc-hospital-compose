package com.luisgmr.ifsc.hospital.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "pessoa_id")

public class Paciente extends Pessoa {
 
	private String tipoSanguineo;
	private String sexo;
	private String nomeSocial;
	private LocalDate dataNascimento;

}
