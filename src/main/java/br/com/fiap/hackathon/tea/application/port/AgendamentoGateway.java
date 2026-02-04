package br.com.fiap.hackathon.tea.application.port;


import br.com.fiap.hackathon.tea.application.model.AgendamentoResponse;

public interface AgendamentoGateway {
    AgendamentoResponse agendar(String protocolo);
}