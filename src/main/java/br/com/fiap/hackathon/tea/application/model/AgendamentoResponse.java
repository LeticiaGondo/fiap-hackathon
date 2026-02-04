package br.com.fiap.hackathon.tea.application.model;

import java.time.LocalDateTime;

public record AgendamentoResponse(
        String protocolo,
        String status,
        LocalDateTime dataHora,
        String unidade,
        String especialidade
) {}