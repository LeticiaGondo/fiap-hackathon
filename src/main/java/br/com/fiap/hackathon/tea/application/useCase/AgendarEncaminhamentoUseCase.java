package br.com.fiap.hackathon.tea.application.useCase;


import br.com.fiap.hackathon.tea.application.model.AgendamentoResponse;
import br.com.fiap.hackathon.tea.application.port.AgendamentoGateway;
import br.com.fiap.hackathon.tea.application.port.EncaminhamentoRepository;
import br.com.fiap.hackathon.tea.domain.exception.ValidacaoException;
import org.springframework.stereotype.Service;

@Service
public class AgendarEncaminhamentoUseCase {

    public static final String ERRO_NAO_VALIDADO =
            "Encaminhamento precisa passar pelo processo de validacao";

    private final EncaminhamentoRepository encaminhamentoRepository;
    private final AgendamentoGateway agendamentoGateway;

    public AgendarEncaminhamentoUseCase(
            EncaminhamentoRepository encaminhamentoRepository,
            AgendamentoGateway agendamentoGateway
    ) {
        this.encaminhamentoRepository = encaminhamentoRepository;
        this.agendamentoGateway = agendamentoGateway;
    }

    public AgendamentoResponse execute(String protocolo) {
        if (!encaminhamentoRepository.existePorProtocolo(protocolo)) {
            throw new ValidacaoException(ERRO_NAO_VALIDADO);
        }

        return agendamentoGateway.agendar(protocolo);
    }
}
