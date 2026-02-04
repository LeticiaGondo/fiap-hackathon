package br.com.fiap.hackathon.validator.domain.entity;

import br.com.fiap.hackathon.validator.domain.Cpf;
import br.com.fiap.hackathon.validator.domain.exception.ValidacaoDominioException;

public class Paciente {

    private final Cpf cpf;
    private String nome;

    public Paciente(Cpf cpf, String nome) {
        if(nome == null || nome.isBlank()) {
            throw new ValidacaoDominioException("Nome do paciente é obrigatório");
        }

        this.cpf = cpf;
        this.nome = nome;
    }
}
