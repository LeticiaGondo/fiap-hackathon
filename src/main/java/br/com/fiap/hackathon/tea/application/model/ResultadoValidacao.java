package br.com.fiap.hackathon.tea.application.model;

import java.util.Collections;
import java.util.List;

public record ResultadoValidacao(List<String> pendencias) {

    public ResultadoValidacao {
        pendencias = pendencias == null ? List.of() : List.copyOf(pendencias);
    }

    public static ResultadoValidacao semPendencias() {
        return new ResultadoValidacao(List.of());
    }

    public static ResultadoValidacao comPendencias(List<String> pendencias) {
        return new ResultadoValidacao(pendencias);
    }

    public boolean possuiPendencias() {
        return !pendencias.isEmpty();
    }

    public List<String> listarPendencias() {
        return Collections.unmodifiableList(pendencias);
    }
}

