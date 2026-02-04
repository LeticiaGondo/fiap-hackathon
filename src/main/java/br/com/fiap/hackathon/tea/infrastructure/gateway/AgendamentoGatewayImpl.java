package br.com.fiap.hackathon.tea.infrastructure.gateway;

import br.com.fiap.hackathon.tea.application.model.AgendamentoResponse;
import br.com.fiap.hackathon.tea.application.port.AgendamentoGateway;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AgendamentoGatewayImpl implements AgendamentoGateway {


    @Override
    public AgendamentoResponse agendar(String protocolo, String especialidade) {
        return new AgendamentoResponse(
                protocolo,
                "AGENDADO",
                LocalDateTime.now().plusDays(3),
                "UBS Central",
                especialidade
        );
    }
}