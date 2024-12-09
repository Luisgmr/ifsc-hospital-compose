package com.luisgmr.ifsc.hospital.enums;


public enum UF {
    AC("Acre", "AC"),
    AL("Alagoas", "AL"),
    AP("Amapá", "AP"),
    AM("Amazonas", "AM"),
    BA("Bahia", "BA"),
    CE("Ceará", "CE"),
    DF("Distrito Federal", "DF"),
    ES("Espírito Santo", "ES"),
    GO("Goiás", "GO"),
    MA("Maranhão", "MA"),
    MT("Mato Grosso", "MT"),
    MS("Mato Grosso do Sul", "MS"),
    MG("Minas Gerais", "MG"),
    PA("Pará", "PA"),
    PB("Paraíba", "PB"),
    PR("Paraná", "PR"),
    PE("Pernambuco", "PE"),
    PI("Piauí", "PI"),
    RJ("Rio de Janeiro", "RJ"),
    RN("Rio Grande do Norte", "RN"),
    RS("Rio Grande do Sul", "RS"),
    RO("Rondônia", "RO"),
    RR("Roraima", "RR"),
    SC("Santa Catarina", "SC"),
    SP("São Paulo", "SP"),
    SE("Sergipe", "SE"),
    TO("Tocantins", "TO");

    private final String nomeCompleto;
    private final String sigla;

    UF(String nomeCompleto, String sigla) {
        this.nomeCompleto = nomeCompleto;
        this.sigla = sigla;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getSigla() {
        return sigla;
    }

    @Override
    public String toString() {
        return nomeCompleto + " (" + sigla + ")";
    }
}

