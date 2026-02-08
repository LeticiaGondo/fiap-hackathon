package br.com.fiap.hackathon.tea.application.useCase;

import br.com.fiap.hackathon.tea.application.model.ResultadoValidacao;
import br.com.fiap.hackathon.tea.application.port.CfmGateway;
import br.com.fiap.hackathon.tea.application.port.EncaminhamentoRepository;
import br.com.fiap.hackathon.tea.domain.Encaminhamento;
import br.com.fiap.hackathon.tea.domain.Medico;
import br.com.fiap.hackathon.tea.domain.exception.ValidacaoException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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


    public ResultadoValidacao execute(Encaminhamento encaminhamento) {

        List<String> pendencias = new ArrayList<>(encaminhamento.validarPendencias());

        String protocolo = encaminhamento.getProtocolo();

        if (protocolo != null && !protocolo.isBlank() && repository.existePorProtocolo(protocolo)) {
            pendencias.add(String.format(ERRO_PROTOCOLO_DUPLICADO, protocolo));
        }

        Medico medico = encaminhamento.getMedico();
        if (medico != null
                && medico.getCrmUf() != null && !medico.getCrmUf().isBlank()
                && medico.getCrmNumero() != null && !medico.getCrmNumero().isBlank()
                && !cfmGateway.existeCrm(medico.getCrmUf(), medico.getCrmNumero())) {
            pendencias.add(ERRO_CRM_INVALIDO);
        }

        if (!pendencias.isEmpty()) {
            return ResultadoValidacao.comPendencias(pendencias);
        }

        repository.salvar(encaminhamento);
        return ResultadoValidacao.semPendencias();

    }

}

