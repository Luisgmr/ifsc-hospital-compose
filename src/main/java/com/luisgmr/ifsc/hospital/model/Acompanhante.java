
package com.luisgmr.ifsc.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "acompanhante")
public class Acompanhante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String nome;
    public String grauParentesco;
    public String cpf;
    public String fone;
    public String email;
    public String status;
}
