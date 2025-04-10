package com.luisgmr.ifsc.hospital.controller;

import com.luisgmr.ifsc.hospital.dao.PessoaDAO;
import com.luisgmr.ifsc.hospital.enums.PessoaType;
import com.luisgmr.ifsc.hospital.model.*;
import java.util.List;

public class PessoasCategoryController {

    private final PessoaDAO pessoaDAO = new PessoaDAO();

    public void load(PessoaType type) {
        ClasseDados dados = ClasseDados.getInstance();
        List<? extends Pessoa> pessoas = pessoaDAO.findAllByType(type);

        switch (type) {
            case PACIENTE -> {
                dados.pacientes.clear();
                dados.pacientes.addAll((List<Paciente>) pessoas);
            }
            case MEDICO -> {
                dados.medicos.clear();
                dados.medicos.addAll((List<Medico>) pessoas);
            }
            case ENFERMEIRO -> {
                dados.enfermeiros.clear();
                dados.enfermeiros.addAll((List<Enfermeiro>) pessoas);
            }
            case FARMACEUTICO -> {
                dados.farmaceuticos.clear();
                dados.farmaceuticos.addAll((List<Farmaceutico>) pessoas);
            }
            case USUARIO -> {
                dados.usuarios.clear();
                dados.usuarios.addAll((List<Usuario>) pessoas);
            }
        }
    }

    public void save(Pessoa pessoa) {
        try {
            pessoaDAO.save(pessoa);
            System.out.println(pessoa.getNome() + " foi inserido(a) com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao salvar a pessoa: " + e.getMessage());
        }
    }

    public void updatePessoa(String cpfCnpj, Pessoa pessoa, PessoaType type) {
        pessoaDAO.updatePessoa(cpfCnpj, pessoa, type);
    }

    public void delete(Pessoa pessoa) {
        pessoaDAO.deleteById(pessoa.getId(), pessoa.getClass());
    }

    public Pessoa getPessoaByCpf(String cpf, PessoaType type) {
        try {
            return pessoaDAO.findByCpfAndType(cpf, type);
        } catch (Exception e) {
            System.err.println("Erro ao buscar pessoa por CPF: " + e.getMessage());
            return null;
        }
    }

}