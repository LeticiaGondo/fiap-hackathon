package br.com.fiap.hackathon.validator.domain.exception;

public class DomainValidationException extends RuntimeException{

    public DomainValidationException(String message) {
            super(message);
        }
}
