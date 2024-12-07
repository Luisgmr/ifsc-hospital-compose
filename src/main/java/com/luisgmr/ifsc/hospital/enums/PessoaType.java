package com.luisgmr.ifsc.hospital.enums;

import com.luisgmr.ifsc.hospital.model.*;

public enum PessoaType {
    PACIENTE("Paciente", Paciente.class),
    MEDICO("Médico", Medico.class),
    ENFERMEIRO("Enfermeiro", Enfermeiro.class),
    FARMACEUTICO("Farmacêutico", Farmaceutico.class),
    USUARIO("Usuário", Usuario.class);

    String displayName;
    Class<? extends Pessoa> cls;

    PessoaType(String displayName, Class<? extends Pessoa> cls) {
        this.displayName = displayName;
        this.cls = cls;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPluralName() {
        return displayName + "s";
    }

    public Class<?> getClassObject() {
        return cls;
    }
}
