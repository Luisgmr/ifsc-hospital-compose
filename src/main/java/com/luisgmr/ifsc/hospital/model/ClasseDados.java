package com.luisgmr.ifsc.hospital.model;

import java.util.ArrayList;
import java.util.List;

public class ClasseDados {

    private static ClasseDados instance;

    public List<Paciente> pacientes = new ArrayList<>();
    public List<Acompanhante> acompanhantes = new ArrayList<>();
    public List<Ala> alas = new ArrayList<>();
    public List<Jornada> jornadas = new ArrayList<>();
    public List<Receita> receitas = new ArrayList<>();
    public List<Exame> exames = new ArrayList<>();
    public List<Medico> medicos = new ArrayList<>();
    public List<Prontuario> prontuarios = new ArrayList<>();
    public List<Leito> leitos = new ArrayList<>();
    public List<Enfermeiro> enfermeiros = new ArrayList<>();
    public List<Atendimento> atendimentos = new ArrayList<>();
    public List<Consulta> consultas = new ArrayList<>();
    public List<Lote> lotes = new ArrayList<>();
    public List<Internacao> internacoes = new ArrayList<>();
    public List<Laboratorio> laboratorios = new ArrayList<>();
    public List<Medicamento> medicamentos = new ArrayList<>();
    public List<Farmaceutico> farmaceuticos = new ArrayList<>();
    public List<Fornecedor> fornecedores = new ArrayList<>();
    public List<Quarto> quartos = new ArrayList<>();

    public static synchronized ClasseDados getInstance(){
        if (instance == null){
            instance = new ClasseDados();
        }
        return instance;
    }

}
