package br.com.fiap.hackathon.tea.api;

import br.com.fiap.hackathon.tea.api.dto.EncaminhamentoRequest;
import br.com.fiap.hackathon.tea.api.dto.EncaminhamentoResponse;
import br.com.fiap.hackathon.tea.api.mapper.EncaminhamentoMapper;
import br.com.fiap.hackathon.tea.application.model.AgendamentoResponse;
import br.com.fiap.hackathon.tea.application.useCase.AgendarEncaminhamentoUseCase;
import br.com.fiap.hackathon.tea.application.useCase.ValidarEncaminhamentoUseCase;
import br.com.fiap.hackathon.tea.domain.Encaminhamento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/encaminhamento")
public class EncaminhamentoController {

    private final ValidarEncaminhamentoUseCase validarEncaminhamentoUseCase;
    private final AgendarEncaminhamentoUseCase agendarEncaminhamentoUseCase;
    private EncaminhamentoMapper encaminhamentoMapper;

    public EncaminhamentoController(ValidarEncaminhamentoUseCase validarEncaminhamentoUseCase, AgendarEncaminhamentoUseCase agendarEncaminhamentoUseCase, EncaminhamentoMapper encaminhamentoMapper) {
        this.validarEncaminhamentoUseCase = validarEncaminhamentoUseCase;
        this.agendarEncaminhamentoUseCase = agendarEncaminhamentoUseCase;
        this.encaminhamentoMapper = encaminhamentoMapper;
    }

    @PostMapping("/validacao")
    public ResponseEntity<EncaminhamentoResponse> validar(@RequestBody EncaminhamentoRequest request) {

        Encaminhamento domain = encaminhamentoMapper.toDomain(request);
        validarEncaminhamentoUseCase.execute(domain);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/agendamento/{protocolo}")
    public ResponseEntity<AgendamentoResponse> agendar(@PathVariable String protocolo) {
        AgendamentoResponse response = agendarEncaminhamentoUseCase.execute(protocolo);
        return ResponseEntity.ok(response);
    }
}