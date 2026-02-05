package br.com.fiap.hackathon.tea.application.useCase;

import br.com.fiap.hackathon.tea.application.exception.EncaminhamentoNaoEncontradoException;
import br.com.fiap.hackathon.tea.application.model.AgendamentoResponse;
import br.com.fiap.hackathon.tea.application.port.AgendamentoGateway;
import br.com.fiap.hackathon.tea.application.port.EncaminhamentoRepository;
import br.com.fiap.hackathon.tea.domain.exception.ValidacaoException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AgendarEncaminhamentoUseCase {

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
            throw new EncaminhamentoNaoEncontradoException(
                    "Encaminhamento não encontrado para o protocolo " + protocolo
            );
        }

        String especialidade = encaminhamentoRepository.buscaEspecialidaePorProtocolo(protocolo);

        return agendamentoGateway.agendar(protocolo, especialidade);
    }
}
