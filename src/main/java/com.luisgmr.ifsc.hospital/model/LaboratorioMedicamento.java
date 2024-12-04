package com.luisgmr.ifsc.hospital.model;


public class LaboratorioMedicamento {
    private long id;
    private String codigoBarras;
    private String observacao;
    private String status;
    private Medicamento medicamento;
    private Laboratorio laboratorio;
    
    
    public LaboratorioMedicamento(){
        
    }

    public LaboratorioMedicamento(long id, String codigoBarras, String observacao, String status, Medicamento medicamento, Laboratorio laboratorio) {
        this.id = id;
        this.codigoBarras = codigoBarras;
        this.observacao = observacao;
        this.status = status;
        this.medicamento = medicamento;
        this.laboratorio = laboratorio;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }   
}
