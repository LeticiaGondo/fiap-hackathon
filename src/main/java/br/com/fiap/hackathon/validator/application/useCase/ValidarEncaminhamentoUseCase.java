package br.com.fiap.hackathon.validator.application.useCase;

import br.com.fiap.hackathon.validator.application.useCase.model.ResultadoValidacao;
import br.com.fiap.hackathon.validator.domain.entity.Encaminhamento;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidarEncaminhamentoUseCase {
    public ResultadoValidacao execute(Encaminhamento encaminhamento) {

        List<String> pendencias = new ArrayList<>();

        return ResultadoValidacao.valido();
    }

}
