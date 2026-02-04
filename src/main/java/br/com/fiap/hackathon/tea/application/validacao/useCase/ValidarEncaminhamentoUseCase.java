package br.com.fiap.hackathon.tea.application.validacao.useCase;

import br.com.fiap.hackathon.tea.application.validacao.repository.EncaminhamentoRepository;
import br.com.fiap.hackathon.tea.domain.encaminhamento.Encaminhamento;
import br.com.fiap.hackathon.tea.domain.encaminhamento.exception.ValidacaoException;
import org.springframework.stereotype.Service;

@Service
public class ValidarEncaminhamentoUseCase {

    public static final String ERRO_PROTOCOLO_DUPLICADO = "Encaminhamento com protocolo %s já existe";


    private final EncaminhamentoRepository repository;

    public ValidarEncaminhamentoUseCase(EncaminhamentoRepository encaminhamentoRepository) {
        this.repository = encaminhamentoRepository;
    }


    public void execute(Encaminhamento encaminhamento) {

        //TODO:Add regra de CID TEA
        //TODO:Add validacao do crm batendo na api OMS

        String protocolo = encaminhamento.getProtocolo();

        if (repository.existePorProtocolo(protocolo)) {
            throw new ValidacaoException(
                    String.format(ERRO_PROTOCOLO_DUPLICADO, protocolo));
        }

       repository.salvar(encaminhamento);

    }

}

