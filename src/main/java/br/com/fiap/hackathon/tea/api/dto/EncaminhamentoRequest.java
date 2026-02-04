package br.com.fiap.hackathon.tea.api.dto;

public record EncaminhamentoRequest(
        String protocolo,
        String cid,
        MedicoRequest medico,
        PacienteRequest paciente
) {
    public record MedicoRequest(String nome, String crmUf, String crmNumero) {}
    public record PacienteRequest(String cpf, String nome) {}}