package br.com.fiap.hackathon.validator.application.useCase;

import br.com.fiap.hackathon.validator.application.model.ResultadoValidacao;
import br.com.fiap.hackathon.validator.application.repository.EncaminhamentoRepository;
import br.com.fiap.hackathon.validator.domain.Encaminhamento;
import br.com.fiap.hackathon.validator.domain.exception.ValidacaoException;
import org.springframework.stereotype.Service;

@Service
public class ValidarEncaminhamentoUseCase {

    public static final String ERRO_PROTOCOLO_DUPLICADO = "Encaminhamento com protocolo %s já existe";


    private final EncaminhamentoRepository repository;

    public ValidarEncaminhamentoUseCase(EncaminhamentoRepository encaminhamentoRepository) {
        this.repository = encaminhamentoRepository;
    }


    public void execute(Encaminhamento encaminhamento) {

        //TODO:Add regras

        String protocolo = encaminhamento.getProtocolo();

        if (repository.existePorProtocolo(protocolo)) {
            throw new ValidacaoException(
                    String.format(ERRO_PROTOCOLO_DUPLICADO, protocolo));
        }

       repository.salvar(encaminhamento);

    }

}

