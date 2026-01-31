package br.com.fiap.hackathon.validator.domain.entity;

import br.com.fiap.hackathon.validator.domain.Crm;

public class Medico {

    private String nome;
    private Crm crm;

    public Medico(String nome, Crm crm) {
        this.nome = nome;
        this.crm = crm;
    }
}
