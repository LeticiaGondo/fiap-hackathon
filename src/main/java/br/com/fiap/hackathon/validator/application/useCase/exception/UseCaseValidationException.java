package br.com.fiap.hackathon.validator.application.useCase.exception;

import java.util.List;

public class UseCaseValidationException extends RuntimeException {

    private final List<String> errors;

    public UseCaseValidationException(List<String> errors) {
        super(String.join("; ", errors));
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}