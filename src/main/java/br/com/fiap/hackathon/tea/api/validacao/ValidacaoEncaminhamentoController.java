package br.com.fiap.hackathon.tea.api.validacao;

import br.com.fiap.hackathon.tea.api.validacao.dto.EncaminhamentoRequest;
import br.com.fiap.hackathon.tea.api.validacao.dto.EncaminhamentoResponse;
import br.com.fiap.hackathon.tea.api.validacao.mapper.EncaminhamentoMapper;
import br.com.fiap.hackathon.tea.application.validacao.useCase.ValidarEncaminhamentoUseCase;
import br.com.fiap.hackathon.tea.domain.encaminhamento.Encaminhamento;
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
        useCase.execute(domain);

        return ResponseEntity.ok().build();
    }
}