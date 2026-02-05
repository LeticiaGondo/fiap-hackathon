package br.com.fiap.hackathon.tea.api;

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
                .body(Map.of("mensagem", "Erro ao consultar serviço externo (CFM)"));
    }

}