package com.luisgmr.ifsc.hospital.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

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
@DiscriminatorValue("PACIENTE")
public class Paciente extends Pessoa {
 
	public String tipoSanguineo;
	public String sexo;
	public String nomeSocial;
	public LocalDate dataNascimento;

}
