package br.com.fiap.hackathon.validator.api;

import br.com.fiap.hackathon.validator.application.useCase.exception.UseCaseValidationException;
import br.com.fiap.hackathon.validator.domain.exception.ValidacaoDominioException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ValidacaoDominioException.class)
    public ResponseEntity<Map<String, String>> handleDomain(ValidacaoDominioException ex) {
        return ResponseEntity.badRequest().body(Map.of("mensagem", ex.getMessage()));
    }

    @ExceptionHandler(UseCaseValidationException.class)
    public ResponseEntity<Map<String, String>> handleUseCase(UseCaseValidationException ex) {
        return ResponseEntity.badRequest().body(Map.of("mensagem", ex.getMessage()));
    }
}