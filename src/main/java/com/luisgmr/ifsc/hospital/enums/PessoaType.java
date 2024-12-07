package com.luisgmr.ifsc.hospital.enums;

public enum PessoaType {
    PACIENTE("Pacientes"),
    MEDICO("Médicos"),
    ENFERMEIRO("Enfermeiros"),
    FARMACEUTICO("Farmacêuticos"),
    USUARIO("Usuários");

    String displayName;

    PessoaType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
