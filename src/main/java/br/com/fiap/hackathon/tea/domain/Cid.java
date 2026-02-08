package br.com.fiap.hackathon.tea.domain;

import br.com.fiap.hackathon.tea.domain.exception.ValidacaoException;

import java.util.ArrayList;
import java.util.List;

public class Cid {

    public static final String ERRO_CID_OBRIGATORIO = "CID é obrigatório";
    public static final String ERRO_CID_TEA = "CID informado não corresponde a um diagnóstico de TEA";

    private final String codigo;


    private Cid(String codigo) {
        this.codigo = codigo;
    }

    public static Cid of(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            return null;
        }
        return new Cid(codigo.toUpperCase());
    }

    public String getCodigo() {
        return codigo;
    }


    public List<String> validarPendencias() {
        List<String> pendencias = new ArrayList<>();

        if (!ehCidTea(codigo)) {
            pendencias.add(ERRO_CID_TEA);
        }

        return pendencias;
    }

    public static boolean ehCidTea(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            return false;
        }
        return codigo.toUpperCase().startsWith("F84");
    }

}
