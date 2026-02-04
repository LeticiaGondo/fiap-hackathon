package br.com.fiap.hackathon.validator.application.useCase;

import br.com.fiap.hackathon.validator.application.model.ResultadoValidacao;
import br.com.fiap.hackathon.validator.application.repository.EncaminhamentoRepository;
import br.com.fiap.hackathon.validator.domain.Encaminhamento;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidarEncaminhamentoUseCase {


    private final EncaminhamentoRepository encaminhamentoRepository;

    public ValidarEncaminhamentoUseCase(EncaminhamentoRepository encaminhamentoRepository) {
        this.encaminhamentoRepository = encaminhamentoRepository;
    }


    public ResultadoValidacao execute(Encaminhamento encaminhamento) {

        //TODO:Add regras

        //se Encaminhamento valido salve
        encaminhamentoRepository.salvar(encaminhamento);

        return ResultadoValidacao.valido();
    }

}

