package br.com.fiap.hackathon.tea.domain;

import br.com.fiap.hackathon.tea.domain.exception.ValidacaoException;

import static br.com.fiap.hackathon.tea.domain.Encaminhamento.ERRO_MEDICO_OBRIGATORIO;

public class Medico {

    public static final String ERRO_CRM_UF = "UF do CRM é obrigatória";
    public static final String ERRO_CRM_NUMERO = "Número do CRM é obrigatório";
    public static final String ERRO_NOME = "Nome do medico(a) é obrigatório";


    private String nome;
    private String crmUf;
    private String crmNumero;

    public Medico(String nome, String crmUf, String crmNumero) {

        if (nome == null || nome.isBlank()) {
            throw new ValidacaoException(ERRO_NOME);
        }

        if (crmUf == null || crmUf.isBlank()) {
            throw new ValidacaoException(ERRO_CRM_UF);
        }

        if (crmNumero == null || crmNumero.isBlank()) {
            throw new ValidacaoException(ERRO_CRM_NUMERO);
        }

        this.nome = nome;
        this.crmUf = crmUf;
        this.crmNumero = crmNumero;
    }

    public String getNome() {
        return nome;
    }

    public String getCrmUf() {
        return crmUf;
    }

    public String getCrmNumero() {
        return crmNumero;
    }
}
