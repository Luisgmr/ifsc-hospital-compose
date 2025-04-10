package com.luisgmr.ifsc.hospital.dao;

import com.luisgmr.ifsc.hospital.config.HibernateConfig;
import com.luisgmr.ifsc.hospital.enums.PessoaType;
import com.luisgmr.ifsc.hospital.model.*;
import com.luisgmr.ifsc.hospital.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.stream.Collectors;

public class PessoaDAO {

    public void save(Pessoa pessoa) {
        HibernateUtil.save(pessoa);
    }

    public <T extends Pessoa> T findById(Long id, Class<T> entityClass) {
        return HibernateUtil.executeAndReturn(session ->
                session.get(entityClass, id)
        );
    }

    public List<? extends Pessoa> findAllByType(PessoaType type) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            String query = "FROM " + type.getClassObject().getName();
            return session.createQuery(query, type.getClassObject()).list()
                    .stream()
                    .map(pessoa -> (Pessoa)pessoa)
                    .toList();
        }
    }

    public List<? extends Pessoa> findAll(PessoaType type) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM " + type.getClassObject().getSimpleName(),
                    type.getClassObject()
            ).getResultList()
                    .stream()
                    .map(pessoa -> (Pessoa) pessoa )
                    .collect(Collectors.toList());
        }
    }


    public void updatePessoa(String cpf, Pessoa novaPessoa, PessoaType type) {
        HibernateUtil.executeInTransaction(session -> {
            // Busca a pessoa existente pelo CPF
            String hql = "FROM " + type.getClassObject().getSimpleName() + " WHERE cpfCnpj = :cpf";
            Pessoa pessoaExistente = (Pessoa) session.createQuery(hql, type.getClassObject())
                    .setParameter("cpf", cpf)
                    .uniqueResult();

            if (pessoaExistente == null) {
                throw new IllegalArgumentException("Pessoa não encontrada com CPF: " + cpf);
            }

            // Atualiza os campos básicos comuns a todas as pessoas
            pessoaExistente.setNome(novaPessoa.getNome());
            pessoaExistente.setFone1(novaPessoa.getFone1());
            pessoaExistente.setFone2(novaPessoa.getFone2());
            pessoaExistente.setEmail(novaPessoa.getEmail());
            pessoaExistente.setRgInscricaoEstadual(novaPessoa.getRgInscricaoEstadual());
            pessoaExistente.setEndereco(novaPessoa.getEndereco());
            pessoaExistente.setCep(novaPessoa.getCep());
            pessoaExistente.setCidade(novaPessoa.getCidade());
            pessoaExistente.setUf(novaPessoa.getUf());
            pessoaExistente.setBairro(novaPessoa.getBairro());
            pessoaExistente.setLogradouro(novaPessoa.getLogradouro());
            pessoaExistente.setComplemento(novaPessoa.getComplemento());

            // Atualiza campos específicos de cada tipo
            switch (type) {
                case PACIENTE -> {
                    Paciente pacienteExistente = (Paciente) pessoaExistente;
                    Paciente novoPaciente = (Paciente) novaPessoa;
                    pacienteExistente.setTipoSanguineo(novoPaciente.getTipoSanguineo());
                    pacienteExistente.setSexo(novoPaciente.getSexo());
                    pacienteExistente.setDataNascimento(novoPaciente.getDataNascimento());
                }
                case MEDICO -> {
                    Medico medicoExistente = (Medico) pessoaExistente;
                    Medico novoMedico = (Medico) novaPessoa;
                    medicoExistente.setCrm(novoMedico.getCrm());
                    medicoExistente.setLogin(novoMedico.getLogin());
                    medicoExistente.setSenha(novoMedico.getSenha());
                }
                case ENFERMEIRO -> {
                    Enfermeiro enfermeiroExistente = (Enfermeiro) pessoaExistente;
                    Enfermeiro novoEnfermeiro = (Enfermeiro) novaPessoa;
                    enfermeiroExistente.setCre(novoEnfermeiro.getCre());
                    enfermeiroExistente.setLogin(novoEnfermeiro.getLogin());
                    enfermeiroExistente.setSenha(novoEnfermeiro.getSenha());
                }
                case FARMACEUTICO -> {
                    Farmaceutico farmaceuticoExistente = (Farmaceutico) pessoaExistente;
                    Farmaceutico novoFarmaceutico = (Farmaceutico) novaPessoa;
                    farmaceuticoExistente.setCfr(novoFarmaceutico.getCfr());
                    farmaceuticoExistente.setLogin(novoFarmaceutico.getLogin());
                    farmaceuticoExistente.setSenha(novoFarmaceutico.getSenha());
                }
                case USUARIO -> {
                    Usuario usuarioExistente = (Usuario) pessoaExistente;
                    Usuario novoUsuario = (Usuario) novaPessoa;
                    usuarioExistente.setLogin(novoUsuario.getLogin());
                    usuarioExistente.setSenha(novoUsuario.getSenha());
                }
            }

            session.merge(pessoaExistente);
            System.out.println("Pessoa atualizada com sucesso!");
        });
    }

    public Pessoa findByCpfAndType(String cpf, PessoaType type) {
        return (Pessoa) HibernateUtil.executeAndReturn(session -> {
            String hql = "FROM " + type.getClassObject().getSimpleName() + " WHERE cpfCnpj = :cpf";
            return session.createQuery(hql, type.getClassObject())
                    .setParameter("cpf", cpf)
                    .uniqueResult();
        });
    }

    public <T extends Pessoa> void deleteById(long id, Class<T> entityClass) {
        HibernateUtil.executeInTransaction(session -> session.delete(findById(id, entityClass)));
    }
}
