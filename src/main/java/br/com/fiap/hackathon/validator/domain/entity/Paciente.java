package br.com.fiap.hackathon.validator.domain.entity;

import br.com.fiap.hackathon.validator.domain.Endereco;

public class Paciente {

    private final String cpf;
    private String nome;
    private Endereco endereco;

    public Paciente(String cpf, String nome, Endereco endereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
    }
}
