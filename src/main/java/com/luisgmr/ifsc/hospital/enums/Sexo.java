package com.luisgmr.ifsc.hospital.enums;

public enum Sexo {
    MASCULINO("Masculino"),
    FEMININO("Feminino");

    String displayName;

    Sexo(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
