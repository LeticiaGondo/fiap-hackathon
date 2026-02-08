package br.com.fiap.hackathon.tea.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PacienteTest {


    @Test
    void deveRetornarPendenciasQuandoDadosInvalidos() {
        Paciente paciente = new Paciente(Cpf.of("123"), "");
        List<String> pendencias = paciente.validarPendencias();

        assertTrue(pendencias.contains(Paciente.ERRO_NOME));
        assertTrue(pendencias.contains(Cpf.ERRO_CPF_INVALIDO));
    }

    @Test
    void deveRetornarPendenciaQuandoCpfNaoInformado() {
        Paciente paciente = new Paciente(null, "Maria");
        assertTrue(paciente.validarPendencias().contains(Cpf.ERRO_CPF_OBRIGATORIO));

    }


}