package br.com.fiap.hackathon.tea.application.port;

public interface CfmGateway {

    boolean existeCrm(String crmUf, String crmNumero);
}
