package br.com.fiap.hackathon.validator.domain.entity;

import br.com.fiap.hackathon.validator.domain.Cpf;
import br.com.fiap.hackathon.validator.domain.Encaminhamento;
import br.com.fiap.hackathon.validator.domain.Medico;
import br.com.fiap.hackathon.validator.domain.Paciente;
import br.com.fiap.hackathon.validator.domain.exception.ValidacaoDominioException;
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
        ValidacaoDominioException ex = assertThrows(
                ValidacaoDominioException.class,
                () -> new Encaminhamento(null, "cid", getMedico(), getPaciente())
        );

        assertEquals("Protocolo é obrigatório", ex.getMessage());
    }

    @Test
    void deveLancarException_quandoProtocoloForVazio() {
        ValidacaoDominioException ex1 = assertThrows(
                ValidacaoDominioException.class,
                () -> new Encaminhamento("", "cid", getMedico(), getPaciente())
        );

        assertEquals("Protocolo é obrigatório", ex1.getMessage());

        ValidacaoDominioException ex2 = assertThrows(
                ValidacaoDominioException.class,
                () -> new Encaminhamento("  ", "cid", getMedico(), getPaciente())
        );

        assertEquals("Protocolo é obrigatório", ex2.getMessage());
    }

    @Test
    void deveLancarException_quandoCidEstiverNulo() {
        ValidacaoDominioException ex = assertThrows(
                ValidacaoDominioException.class,
                () -> new Encaminhamento("123", null, getMedico(), getPaciente())
        );

        assertEquals("CID é obrigatório", ex.getMessage());
    }

    @Test
    void deveLancarException_quandoCidForVazio() {
        ValidacaoDominioException ex1 = assertThrows(
                ValidacaoDominioException.class,
                () -> new Encaminhamento("123", "", getMedico(), getPaciente())
        );

        assertEquals("CID é obrigatório", ex1.getMessage());

        ValidacaoDominioException ex2 = assertThrows(
                ValidacaoDominioException.class,
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