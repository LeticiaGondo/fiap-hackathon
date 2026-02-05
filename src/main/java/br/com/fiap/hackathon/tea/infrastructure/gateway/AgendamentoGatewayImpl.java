package br.com.fiap.hackathon.tea.infrastructure.gateway;

import br.com.fiap.hackathon.tea.application.exception.AgendamentoIndisponivelException;
import br.com.fiap.hackathon.tea.application.exception.IntegrationException;
import br.com.fiap.hackathon.tea.application.model.AgendamentoResponse;
import br.com.fiap.hackathon.tea.application.model.UnidadeAgendamento;
import br.com.fiap.hackathon.tea.application.port.AgendamentoGateway;
import br.com.fiap.hackathon.tea.domain.exception.ValidacaoException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class AgendamentoGatewayImpl implements AgendamentoGateway {

    private static final String MENSAGEM_PADRAO = "Erro ao consultar serviço externo (Agendamento)";
    private static final String REQUISICAO_INVALIDA = "Requisição inválida para agendamento";

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    public AgendamentoGatewayImpl(
            RestClient.Builder builder,
            ObjectMapper objectMapper,
            @Value("${integrations.agendamento.base-url}") String baseUrl
    ) {
        this.restClient = builder.baseUrl(baseUrl).build();
        this.objectMapper = objectMapper;
    }


    @Override
    public AgendamentoResponse agendar(String protocolo, String especialidade) {
        try {
            ExternalAgendamentoResponse response = restClient.post()
                    .uri("/api/agendamentos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new AgendamentoRequest(protocolo, especialidade))
                    .retrieve()
                    .body(ExternalAgendamentoResponse.class);

            if (response == null) {
                throw new IntegrationException(MENSAGEM_PADRAO);
            }

            return new AgendamentoResponse(
                    response.protocolo(),
                    response.status(),
                    response.dataHora(),
                    response.unidade(),
                    especialidade
            );
        } catch (RestClientResponseException ex) {
            HttpStatus status = HttpStatus.valueOf(ex.getRawStatusCode());
            if (status == HttpStatus.CONFLICT) {
                throw new AgendamentoIndisponivelException(extractMensagem(ex.getResponseBodyAsString()));
            }
            if (status == HttpStatus.BAD_REQUEST) {
                throw new ValidacaoException(extractMensagem(ex.getResponseBodyAsString(), REQUISICAO_INVALIDA));
            }
            if (status.is5xxServerError()) {
                throw new IntegrationException(MENSAGEM_PADRAO);
            }
            throw new IntegrationException(MENSAGEM_PADRAO);
        } catch (RestClientException ex) {
            throw new IntegrationException(MENSAGEM_PADRAO);
        }
    }

    private String extractMensagem(String responseBody) {
        return extractMensagem(responseBody, "Sem vagas disponíveis");
    }

    private String extractMensagem(String responseBody, String mensagemPadrao) {
        if (!StringUtils.hasText(responseBody)) {
            return mensagemPadrao;
        }
        try {
            JsonNode node = objectMapper.readTree(responseBody);
            if (node.hasNonNull("mensagem")) {
                return node.get("mensagem").asText(mensagemPadrao);
            }
        } catch (IOException ignored) {
            return mensagemPadrao;
        }
        return mensagemPadrao;
    }

    private record AgendamentoRequest(String protocolo, String especialidade) {
    }

    private record ExternalAgendamentoResponse(
            String protocolo,
            String status,
            LocalDateTime dataHora,
            UnidadeAgendamento unidade
    ) {}
}