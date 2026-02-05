package br.com.fiap.hackathon.tea.domain.entity;

import br.com.fiap.hackathon.tea.domain.*;
import br.com.fiap.hackathon.tea.domain.exception.ValidacaoException;
import org.junit.jupiter.api.Test;

import static br.com.fiap.hackathon.tea.domain.Encaminhamento.*;
import static org.junit.jupiter.api.Assertions.*;

class EncaminhamentoTest {


    @Test
    void deveCriarEncaminhamento_quandoDadosForemValidos() {
        Encaminhamento encaminhamento = new Encaminhamento("protocolo", getCid(), getMedico(), getPaciente(), "especialidade", "motivoSolicitacao");

        assertNotNull(encaminhamento);
    }

    @Test
    void deveLancarException_quandoCampoObrigatorioForNulo() {
        ValidacaoException protocoloEx = assertThrows(
                ValidacaoException.class,
                () -> new Encaminhamento(null, getCid(), getMedico(), getPaciente(),"especialidade", "motivoSolicitacao"));
        assertEquals(ERRO_PROTOCOLO_OBRIGATORIO, protocoloEx.getMessage());

        ValidacaoException especialidadeEx = assertThrows(
                ValidacaoException.class,
                () -> new Encaminhamento("123", getCid(), getMedico(), getPaciente(), null, "motivoSolicitacao")
        );
        assertEquals(ERRO_ESPECIALIDADE_OBRIGATORIO, especialidadeEx.getMessage());

        ValidacaoException motivoEx = assertThrows(
                ValidacaoException.class,
                () -> new Encaminhamento("123", getCid(), getMedico(), getPaciente(), "especialidade", null)
        );
        assertEquals(ERRO_MOTIVO_SOLICITACAO_OBRIGATORIO, motivoEx.getMessage());

        ValidacaoException medicoEx = assertThrows(
                ValidacaoException.class,
                () -> new Encaminhamento("123", getCid(), null, getPaciente(), "especialidade", null)
        );
        assertEquals(ERRO_MEDICO_OBRIGATORIO, medicoEx.getMessage());

        ValidacaoException pacienteEx = assertThrows(
                ValidacaoException.class,
                () -> new Encaminhamento("123", getCid(), getMedico(), null, "especialidade", null)
        );
        assertEquals(ERRO_PACIENTE_OBRIGATORIO, pacienteEx.getMessage());

    }

    @Test
    void deveLancarException_quandoCampoObrigatorioForVazio() {
        ValidacaoException protocoloEx = assertThrows(
                ValidacaoException.class,
                () -> new Encaminhamento("", getCid(), getMedico(), getPaciente(),"especialidade", "motivoSolicitacao"));
        assertEquals(ERRO_PROTOCOLO_OBRIGATORIO, protocoloEx.getMessage());


        ValidacaoException especialidadeEx = assertThrows(
                ValidacaoException.class,
                () -> new Encaminhamento("123", getCid(), getMedico(), getPaciente(), "   ", "motivoSolicitacao")
        );
        assertEquals(ERRO_ESPECIALIDADE_OBRIGATORIO, especialidadeEx.getMessage());

        ValidacaoException motivoEx = assertThrows(
                ValidacaoException.class,
                () -> new Encaminhamento("123", getCid(), getMedico(), getPaciente(), "especialidade", "    ")
        );
        assertEquals(ERRO_MOTIVO_SOLICITACAO_OBRIGATORIO, motivoEx.getMessage());
    }



    private Medico getMedico(){
        return new Medico("Joao", "SP", "123");
    }

    private Paciente getPaciente(){
        return new Paciente(new Cpf("52998224725"), "Maria");
    }
    private Cid getCid(){
        return new Cid("F84.0");
    }
}