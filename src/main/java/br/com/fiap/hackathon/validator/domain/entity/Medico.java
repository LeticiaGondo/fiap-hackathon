package br.com.fiap.hackathon.validator.domain.entity;

import br.com.fiap.hackathon.validator.domain.Crm;

public class Medico {

    private String nome;
    private String crmUf;
    private String crmNumero;

    public Medico(String nome, String crmUf, String crmNumero) {
        this.nome = nome;
        this.crmUf = crmUf;
        this.crmNumero = crmNumero;
    }


}
