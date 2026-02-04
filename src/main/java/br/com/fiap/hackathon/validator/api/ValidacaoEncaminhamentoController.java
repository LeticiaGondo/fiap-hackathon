package br.com.fiap.hackathon.validator.api;

import br.com.fiap.hackathon.validator.api.dto.EncaminhamentoRequest;
import br.com.fiap.hackathon.validator.api.dto.EncaminhamentoResponse;
import br.com.fiap.hackathon.validator.application.useCase.ValidarEncaminhamentoUseCase;
import br.com.fiap.hackathon.validator.application.model.ResultadoValidacao;
import br.com.fiap.hackathon.validator.domain.Encaminhamento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validacao")
public class ValidacaoEncaminhamentoController {

    private final ValidarEncaminhamentoUseCase useCase;
       private EncaminhamentoMapper encaminhamentoMapper;

    public ValidacaoEncaminhamentoController(ValidarEncaminhamentoUseCase useCase, EncaminhamentoMapper encaminhamentoMapper) {
        this.useCase = useCase;
        this.encaminhamentoMapper = encaminhamentoMapper;
    }

    @PostMapping("/encaminhamento")
    public ResponseEntity<EncaminhamentoResponse> validar(@RequestBody EncaminhamentoRequest request) {

        Encaminhamento domain = encaminhamentoMapper.toDomain(request);
        ResultadoValidacao resultado = useCase.execute(domain);

        EncaminhamentoResponse response =
                new EncaminhamentoResponse(resultado.getPendencias());

        return ResponseEntity.ok().build();
    }
}