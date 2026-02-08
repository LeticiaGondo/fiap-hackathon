package br.com.fiap.hackathon.tea.infrastructure.gateway;

import br.com.fiap.hackathon.tea.application.exception.IntegrationException;
import br.com.fiap.hackathon.tea.application.port.CfmGateway;
import br.com.fiap.hackathon.tea.domain.exception.ValidacaoException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

import java.io.IOException;

@Component
public class CfmGatewayHttpImpl implements CfmGateway {

    private static final String CRM_INVALIDO = "CRM inválido";
    private static final String FALHA_AUTENTICACAO = "Falha de autenticação na API CFM";
    private static final String CFM_INDISPONIVEL = "CFM indisponível";

    private final RestClient restClient;
    private final String token;
    private final ObjectMapper objectMapper;

    public CfmGatewayHttpImpl(
            RestClient.Builder builder,
            ObjectMapper objectMapper,
            @Value("${integrations.cfm.base-url}") String baseUrl,
            @Value("${integrations.cfm.token}") String token
    ) {
        this.restClient = builder.baseUrl(baseUrl).build();
        this.objectMapper = objectMapper;
        this.token = token;
    }

    /**
     * Contrato Swagger (SIEM Serviços - Login Único / getPrestadorByCrmUf):
     * - Método: GET
     * - Path: /prestador
     * - Query params: crmUf, crmNumero
     * - Headers obrigatórios: Authorization, Accept: application/json
     * - Respostas:
     *   - 200: prestador encontrado (JSON)
     *   - 400: CRM inválido (JSON com mensagem)
     *   - 401/403: falha de autenticação/autorização
     *   - 404: CRM não encontrado
     *   - 5xx: indisponibilidade
     */
    @Override
    public boolean existeCrm(String crmUf, String crmNumero) {
        try {
            restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/prestador")
                            .queryParam("crmUf", crmUf)
                            .queryParam("crmNumero", crmNumero)
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .headers(headers -> {
                        if (StringUtils.hasText(token)) {
                            headers.set(HttpHeaders.AUTHORIZATION, token);
                        }
                    })
                    .retrieve()
                    .toBodilessEntity();
            return true;
        } catch (RestClientResponseException ex) {
            HttpStatus status = HttpStatus.valueOf(ex.getRawStatusCode());
            if (status == HttpStatus.NOT_FOUND) {
                return false;
            }
            if (status == HttpStatus.BAD_REQUEST) {
               return false;
            }
            if (status == HttpStatus.UNAUTHORIZED || status == HttpStatus.FORBIDDEN) {
                throw new IntegrationException(FALHA_AUTENTICACAO);
            }
            if (status.is5xxServerError()) {
                throw new IntegrationException(CFM_INDISPONIVEL);
            }
            throw new IntegrationException(CFM_INDISPONIVEL);
        } catch (RestClientException ex) {
            throw new IntegrationException(CFM_INDISPONIVEL);
        }
    }

    private String extractMensagem(String responseBody) {
        if (!StringUtils.hasText(responseBody)) {
            return CRM_INVALIDO;
        }
        try {
            JsonNode node = objectMapper.readTree(responseBody);
            if (node.hasNonNull("mensagem")) {
                return node.get("mensagem").asText(CRM_INVALIDO);
            }
        } catch (IOException ignored) {
            return CRM_INVALIDO;
        }
        return CRM_INVALIDO;
    }
}
