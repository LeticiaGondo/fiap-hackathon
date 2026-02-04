package br.com.fiap.hackathon.tea.domain.entity;

import br.com.fiap.hackathon.tea.domain.encaminhamento.Cpf;
import br.com.fiap.hackathon.tea.domain.encaminhamento.Paciente;
import br.com.fiap.hackathon.tea.domain.encaminhamento.exception.ValidacaoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacienteTest {


    @Test
    void deveLancarException_quandoNomeForNulo() {
        Cpf cpf = new Cpf("69145527032");
        assertThrows(ValidacaoException.class, () -> new Paciente(cpf, null));

    }

    @Test
    void deveLancarException_quandoNomeForVazio() {
        Cpf cpf = new Cpf("69145527032");
        assertThrows(ValidacaoException.class, () -> new Paciente(cpf, ""));

    }

    @Test
    void deveLancarException_quandoNomeForrEmBranco() {
        Cpf cpf = new Cpf("69145527032");
        assertThrows(ValidacaoException.class, () -> new Paciente(cpf, " "));

    }


}