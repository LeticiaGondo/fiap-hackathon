package br.com.fiap.hackathon.validator.domain.entity;

import br.com.fiap.hackathon.validator.domain.Cpf;
import br.com.fiap.hackathon.validator.domain.Paciente;
import br.com.fiap.hackathon.validator.domain.exception.ValidacaoException;
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