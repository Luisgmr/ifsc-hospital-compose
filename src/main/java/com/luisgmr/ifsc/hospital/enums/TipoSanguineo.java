package com.luisgmr.ifsc.hospital.enums;

public enum TipoSanguineo {

    O_POSITIVO("O+"),
    O_NEGATIVO("O-"),
    A_POSITIVO("A+"),
    A_NEGATIVO("A-"),
    AB_POSITIVO("AB+"),
    AB_NEGATIVO("AB-"),
    B_POSITIVO("B+"),
    B_NEGATIVO("B-");

    String displayName;

    TipoSanguineo(String displayName) {
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
