package br.com.fiap.hackathon.tea.api.dto;

import java.util.List;

public class EncaminhamentoResponse { ;
    public List<String> pendencias;

    public EncaminhamentoResponse(List<String> pendencias) {
        this.pendencias = pendencias;
    }
}