package br.com.fiap.hackathon.validator.domain.exception;

public class CpfInvalidoException extends RuntimeException{

    public CpfInvalidoException(String message) {
        super(message);
    }
}
