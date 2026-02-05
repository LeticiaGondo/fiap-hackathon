package br.com.fiap.hackathon.tea.application.exception;

public class AgendamentoIndisponivelException extends RuntimeException {

    public AgendamentoIndisponivelException(String message) {
        super(message);
    }
}
