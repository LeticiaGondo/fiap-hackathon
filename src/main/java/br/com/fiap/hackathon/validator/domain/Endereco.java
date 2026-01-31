package br.com.fiap.hackathon.validator.domain;

public class Endereco {

    private final String cep;
    private final String estado;
    private final String cidade;
    private final String rua;
    private final String numero;
    private final String complemento;

    public Endereco(String cep, String estado, String cidade, String rua, String numero, String complemento) {
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }
}
