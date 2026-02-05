package br.com.fiap.hackathon.tea.application.useCase;

import br.com.fiap.hackathon.tea.application.port.CfmGateway;
import br.com.fiap.hackathon.tea.application.port.EncaminhamentoRepository;
import br.com.fiap.hackathon.tea.domain.Encaminhamento;
import br.com.fiap.hackathon.tea.domain.exception.ValidacaoException;
import org.springframework.stereotype.Service;

@Service
public class ValidarEncaminhamentoUseCase {

    public static final String ERRO_PROTOCOLO_DUPLICADO = "Encaminhamento com protocolo %s já existe";
    public static final String ERRO_CRM_INVALIDO = "CRM inválido";
    private final CfmGateway cfmGateway;

    private final EncaminhamentoRepository repository;

    public ValidarEncaminhamentoUseCase(CfmGateway cfmGateway, EncaminhamentoRepository encaminhamentoRepository) {
        this.cfmGateway = cfmGateway;
        this.repository = encaminhamentoRepository;
    }


    public void execute(Encaminhamento encaminhamento) {

        String protocolo = encaminhamento.getProtocolo();

        if (repository.existePorProtocolo(protocolo)) {
            throw new ValidacaoException(
                    String.format(ERRO_PROTOCOLO_DUPLICADO, protocolo));
        }

        String crmUf = encaminhamento.getMedico().getCrmUf();
        String crmNumero = encaminhamento.getMedico().getCrmNumero();

        if (!cfmGateway.existeCrm(crmUf, crmNumero)) {
            throw new ValidacaoException(ERRO_CRM_INVALIDO);
        }

       repository.salvar(encaminhamento);

    }

}

