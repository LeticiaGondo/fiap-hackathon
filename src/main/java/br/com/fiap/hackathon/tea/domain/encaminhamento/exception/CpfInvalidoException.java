package br.com.fiap.hackathon.tea.domain.encaminhamento.exception;

public class CpfInvalidoException extends RuntimeException{

    public CpfInvalidoException(String message) {
        super(message);
    }
}
