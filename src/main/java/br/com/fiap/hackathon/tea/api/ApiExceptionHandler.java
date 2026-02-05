package br.com.fiap.hackathon.tea.api;

import br.com.fiap.hackathon.tea.application.exception.AgendamentoIndisponivelException;
import br.com.fiap.hackathon.tea.application.exception.EncaminhamentoNaoEncontradoException;
import br.com.fiap.hackathon.tea.application.exception.IntegrationException;
import br.com.fiap.hackathon.tea.domain.exception.ValidacaoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<Map<String, String>> handleDomain(ValidacaoException ex) {
        return ResponseEntity.badRequest().body(Map.of("mensagem", ex.getMessage()));
    }

    @ExceptionHandler(IntegrationException.class)
    public ResponseEntity<Map<String, String>> handleIntegration(IntegrationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(Map.of("mensagem", "Erro ao consultar serviço externo"));
    }

    @ExceptionHandler(EncaminhamentoNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(EncaminhamentoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", ex.getMessage()));
    }

    @ExceptionHandler(AgendamentoIndisponivelException.class)
    public ResponseEntity<Map<String, String>> handleSemVagas(AgendamentoIndisponivelException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("mensagem", ex.getMessage()));
    }

}