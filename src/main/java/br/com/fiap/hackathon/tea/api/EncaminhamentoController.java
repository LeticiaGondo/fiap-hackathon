package br.com.fiap.hackathon.tea.api;

import br.com.fiap.hackathon.tea.api.dto.EncaminhamentoRequest;
import br.com.fiap.hackathon.tea.api.dto.EncaminhamentoResponse;
import br.com.fiap.hackathon.tea.api.mapper.EncaminhamentoMapper;
import br.com.fiap.hackathon.tea.application.useCase.ValidarEncaminhamentoUseCase;
import br.com.fiap.hackathon.tea.domain.Encaminhamento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/encaminhamento")
public class EncaminhamentoController {

    private final ValidarEncaminhamentoUseCase useCase;
       private EncaminhamentoMapper encaminhamentoMapper;

    public EncaminhamentoController(ValidarEncaminhamentoUseCase useCase, EncaminhamentoMapper encaminhamentoMapper) {
        this.useCase = useCase;
        this.encaminhamentoMapper = encaminhamentoMapper;
    }

    @PostMapping("/validacao")
    public ResponseEntity<EncaminhamentoResponse> validar(@RequestBody EncaminhamentoRequest request) {

        Encaminhamento domain = encaminhamentoMapper.toDomain(request);
        useCase.execute(domain);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/agendamento/{protocolo}")
    public ResponseEntity<EncaminhamentoResponse> agendar(@PathVariable String protocolo) {


        return ResponseEntity.ok().build();
    }
}