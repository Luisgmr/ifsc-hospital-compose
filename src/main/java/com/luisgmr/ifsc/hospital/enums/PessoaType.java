package com.luisgmr.ifsc.hospital.enums;

import com.luisgmr.ifsc.hospital.model.*;

public enum PessoaType {
    PACIENTE("Pacientes", Paciente.class),
    MEDICO("Médicos", Medico.class),
    ENFERMEIRO("Enfermeiros", Enfermeiro.class),
    FARMACEUTICO("Farmacêuticos", Farmaceutico.class),
    USUARIO("Usuários", Usuario.class);

    String displayName;
    Class<? extends Pessoa> cls;

    PessoaType(String displayName, Class<? extends Pessoa> cls) {
        this.displayName = displayName;
        this.cls = cls;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Class<?> getClassObject() {
        return cls;
    }
}
