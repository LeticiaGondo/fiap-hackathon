package br.com.fiap.hackathon.tea.domain.entity;

import br.com.fiap.hackathon.tea.domain.Cpf;
import br.com.fiap.hackathon.tea.domain.Encaminhamento;
import br.com.fiap.hackathon.tea.domain.Medico;
import br.com.fiap.hackathon.tea.domain.Paciente;
import br.com.fiap.hackathon.tea.domain.exception.ValidacaoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncaminhamentoTest {


    @Test
    void deveCriarEncaminhamento_quandoDadosForemValidos() {
        Encaminhamento encaminhamento = new Encaminhamento("protocolo", "cid", getMedico(), getPaciente());

        assertNotNull(encaminhamento);
    }

    @Test
    void deveLancarException_quandoProtocoloEstiverNulo() {
        ValidacaoException ex = assertThrows(
                ValidacaoException.class,
                () -> new Encaminhamento(null, "cid", getMedico(), getPaciente())
        );

        assertEquals("Protocolo é obrigatório", ex.getMessage());
    }

    @Test
    void deveLancarException_quandoProtocoloForVazio() {
        ValidacaoException ex1 = assertThrows(
                ValidacaoException.class,
                () -> new Encaminhamento("", "cid", getMedico(), getPaciente())
        );

        assertEquals("Protocolo é obrigatório", ex1.getMessage());

        ValidacaoException ex2 = assertThrows(
                ValidacaoException.class,
                () -> new Encaminhamento("  ", "cid", getMedico(), getPaciente())
        );

        assertEquals("Protocolo é obrigatório", ex2.getMessage());
    }

    @Test
    void deveLancarException_quandoCidEstiverNulo() {
        ValidacaoException ex = assertThrows(
                ValidacaoException.class,
                () -> new Encaminhamento("123", null, getMedico(), getPaciente())
        );

        assertEquals("CID é obrigatório", ex.getMessage());
    }

    @Test
    void deveLancarException_quandoCidForVazio() {
        ValidacaoException ex1 = assertThrows(
                ValidacaoException.class,
                () -> new Encaminhamento("123", "", getMedico(), getPaciente())
        );

        assertEquals("CID é obrigatório", ex1.getMessage());

        ValidacaoException ex2 = assertThrows(
                ValidacaoException.class,
                () -> new Encaminhamento("123", " ", getMedico(), getPaciente())
        );

        assertEquals("CID é obrigatório", ex2.getMessage());
    }


    private Medico getMedico(){
        return new Medico("Joao", "SP", "123");
    }

    private Paciente getPaciente(){
        return new Paciente(new Cpf("52998224725"), "Maria");
    }
}