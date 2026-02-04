package br.com.fiap.hackathon.tea.domain.exception;

public class CpfInvalidoException extends RuntimeException{

    public CpfInvalidoException(String message) {
        super(message);
    }
}
