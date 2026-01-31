package br.com.fiap.hackathon.validator.api;

import br.com.fiap.hackathon.validator.application.useCase.exception.UseCaseValidationException;
import br.com.fiap.hackathon.validator.domain.exception.DomainValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(DomainValidationException.class)
    public ResponseEntity<Map<String, String>> handleDomain(DomainValidationException ex) {
        return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(UseCaseValidationException.class)
    public ResponseEntity<Map<String, String>> handleUseCase(UseCaseValidationException ex) {
        return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
    }
}