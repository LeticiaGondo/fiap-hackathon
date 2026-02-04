package br.com.fiap.hackathon.validator.domain.entity;

import br.com.fiap.hackathon.validator.domain.exception.ValidacaoDominioException;

public class Medico {

    private String nome;
    private String crmUf;
    private String crmNumero;

    public Medico(String nome, String crmUf, String crmNumero) {

        if (nome == null || nome.isBlank()) {
            throw new ValidacaoDominioException("Nome do médico é obrigatório");
        }

        if (crmUf == null || crmUf.isBlank()) {
            throw new ValidacaoDominioException("UF do CRM é obrigatória");
        }

        if (crmNumero == null || crmNumero.isBlank()) {
            throw new ValidacaoDominioException("Número do CRM é obrigatório");
        }

        this.nome = nome;
        this.crmUf = crmUf;
        this.crmNumero = crmNumero;
    }


}
