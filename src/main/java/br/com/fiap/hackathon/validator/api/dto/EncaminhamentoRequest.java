package br.com.fiap.hackathon.validator.api.dto;

public class EncaminhamentoRequest {

    String protocolo;
    String cid;
    MedicoRequest medico;
    PacienteRequest paciente;

    public String getProtocolo() {
        return protocolo;
    }

    public String getCid() {
        return cid;
    }

    public MedicoRequest getMedico() {
        return medico;
    }

    public PacienteRequest getPaciente() {
        return paciente;
    }

    public static class MedicoRequest {
        String nome;
        String crmUf;
        String crmNumero;
    }

    public static class PacienteRequest {
        String cpf;
        String nome;
        EnderecoRequest endereco;
    }

    public static class EnderecoRequest {
        String cep;
        String estado;
        String cidade;
        String rua;
        String numero;
    }
}