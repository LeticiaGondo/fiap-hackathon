package br.com.fiap.hackathon.tea.domain;

import br.com.fiap.hackathon.tea.domain.exception.ValidacaoException;

public class Cid {

    public static final String ERRO_CID_OBRIGATORIO = "CID é obrigatório";
    public static final String ERRO_CID_TEA = "CID informado não corresponde a um diagnóstico de TEA";

    private final String codigo;


    public Cid(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new ValidacaoException(ERRO_CID_OBRIGATORIO);
        }

        this.codigo = codigo.toUpperCase();

        if (!ehCidTea()) {
            throw new ValidacaoException(
                    ERRO_CID_TEA
            );
        }
    }

    public String getCodigo() {
        return codigo;
    }

    private boolean ehCidTea() {
        return codigo.startsWith("F84");
    }

}
