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
@Table(name = "laboratorio_medicamento")
public class LaboratorioMedicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String codigoBarras;
    public String observacao;
    public String status;
    @ManyToOne
    @JoinColumn(name = "medicamento_id")
    public Medicamento medicamento;

    @ManyToOne
    @JoinColumn(name = "laboratorio_id")
    public Laboratorio laboratorio;

}
