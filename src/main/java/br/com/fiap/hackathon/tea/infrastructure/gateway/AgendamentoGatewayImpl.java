package br.com.fiap.hackathon.tea.infrastructure.gateway;

import br.com.fiap.hackathon.tea.application.model.AgendamentoResponse;
import br.com.fiap.hackathon.tea.application.port.AgendamentoGateway;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class AgendamentoGatewayImpl implements AgendamentoGateway {

    private final RestClient restClient;

    public AgendamentoGatewayImpl() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:9090")
                .build();
    }

    @Override
    public AgendamentoResponse agendar(String protocolo) {
        return restClient.post()
                .uri("/agendamento/{protocolo}", protocolo)
                .retrieve()
                .body(AgendamentoResponse.class);
    }
}