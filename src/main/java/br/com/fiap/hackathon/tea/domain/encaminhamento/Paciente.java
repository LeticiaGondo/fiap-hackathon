package br.com.fiap.hackathon.tea.domain.encaminhamento;

import br.com.fiap.hackathon.tea.domain.encaminhamento.exception.ValidacaoException;

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
