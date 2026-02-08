package br.com.fiap.hackathon.tea.domain;

import br.com.fiap.hackathon.tea.domain.exception.ValidacaoException;

import java.util.ArrayList;
import java.util.List;

public class Paciente {

    public static final String ERRO_NOME = "Nome do paciente é obrigatório";

    private final Cpf cpf;
    private String nome;

    public Paciente(Cpf cpf, String nome) {

        this.cpf = cpf;
        this.nome = nome;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public List<String> validarPendencias() {
        List<String> pendencias = new ArrayList<>();

        if (nome == null || nome.isBlank()) {
            pendencias.add(ERRO_NOME);
        }

        if (cpf == null) {
            pendencias.add(Cpf.ERRO_CPF_OBRIGATORIO);
        } else {
            pendencias.addAll(cpf.validarPendencias());
        }

        return pendencias;
    }
}
