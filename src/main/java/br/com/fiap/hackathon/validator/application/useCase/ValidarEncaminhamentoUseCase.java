package br.com.fiap.hackathon.validator.application.useCase;

import br.com.fiap.hackathon.validator.application.useCase.exception.UseCaseValidationException;
import br.com.fiap.hackathon.validator.application.useCase.model.ResultadoValidacao;
import br.com.fiap.hackathon.validator.domain.entity.Encaminhamento;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidarEncaminhamentoUseCase {
    public ResultadoValidacao execute(Encaminhamento encaminhamento) {

        List<String> pendencias = new ArrayList<>();

        if (encaminhamento.getCid() == null || encaminhamento.getCid().isBlank()) {
            pendencias.add("CID é obrigatório");
        }

        if (encaminhamento.getMedico() == null) {
            pendencias.add("Médico é obrigatório");
        }

        if (encaminhamento.getPaciente() == null) {
            pendencias.add("Paciente é obrigatório");
        }

        if (!pendencias.isEmpty()) {
            throw new UseCaseValidationException(pendencias);
        }

        return ResultadoValidacao.valido();
    }

}
