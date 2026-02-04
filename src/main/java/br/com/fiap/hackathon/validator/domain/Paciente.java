package br.com.fiap.hackathon.validator.domain;

import br.com.fiap.hackathon.validator.domain.exception.ValidacaoException;

public class Paciente {

    private final Cpf cpf;
    private String nome;

    public Paciente(Cpf cpf, String nome) {
        if(nome == null || nome.isBlank()) {
            throw new ValidacaoException("Nome do paciente é obrigatório");
        }

        this.cpf = cpf;
        this.nome = nome;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }
}
