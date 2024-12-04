package com.luisgmr.ifsc.hospital.model;


public class Laboratorio {
    private String nomeFantasia;
    private String contato;
    private String status;
    
    public Laboratorio(){
        
    }

    public Laboratorio(String nomeFantasia, String contato, String status) {
        this.nomeFantasia = nomeFantasia;
        this.contato = contato;
        this.status = status;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
        
}
