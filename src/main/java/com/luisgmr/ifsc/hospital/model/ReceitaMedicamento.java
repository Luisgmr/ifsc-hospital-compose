package com.luisgmr.ifsc.hospital.model;

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
@Table(name = "receita_medicamento")
public class ReceitaMedicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String prescricao;
    public String status;

    @ManyToOne
    @JoinColumn(name = "receita_id")
    public Receita receita;

    @ManyToOne
    @JoinColumn(name = "medicamento_id")
    public Medicamento medicamento;

}
