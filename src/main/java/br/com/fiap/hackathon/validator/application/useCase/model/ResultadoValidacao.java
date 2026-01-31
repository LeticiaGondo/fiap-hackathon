package br.com.fiap.hackathon.validator.application.useCase.model;

import java.util.List;

public class ResultadoValidacao {
    private List<String> pendencias;

    public ResultadoValidacao(List<String> pendencias) {
        this.pendencias = pendencias;
    }

    public List<String> getPendencias() {
        return pendencias;
    }

    public void setPendencias(List<String> pendencias) {
        this.pendencias = pendencias;
    }

    public static ResultadoValidacao valido() {
        return new ResultadoValidacao( List.of());
    }
}
