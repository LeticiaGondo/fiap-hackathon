package br.com.fiap.hackathon.tea.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EncaminhamentoTest {


    @Test
    void deveRetornarSemPendenciasQuandoDadosForemValidos() {
        Encaminhamento encaminhamento = new Encaminhamento(
                "protocolo",
                Cid.of("F84.0"),
                new Medico("Joao", "SP", "123"),
                new Paciente(Cpf.of("52998224725"), "Maria"),
                "especialidade",
                "motivo"
        );
        assertTrue(encaminhamento.validarPendencias().isEmpty());
    }

    @Test
    void deveRetornarPendenciasAgregadasQuandoDadosInvalidos() {
        Encaminhamento encaminhamento = new Encaminhamento(
                "",
                Cid.of("F90.0"),
                new Medico("", "", ""),
                new Paciente(Cpf.of("123"), ""),
                "",
                ""
        );

        List<String> pendencias = encaminhamento.validarPendencias();
        assertTrue(pendencias.contains(Encaminhamento.ERRO_PROTOCOLO_OBRIGATORIO));
        assertTrue(pendencias.contains(Encaminhamento.ERRO_ESPECIALIDADE_OBRIGATORIO));
        assertTrue(pendencias.contains(Encaminhamento.ERRO_MOTIVO_SOLICITACAO_OBRIGATORIO));
        assertTrue(pendencias.contains(Cid.ERRO_CID_TEA));
        assertTrue(pendencias.contains(Medico.ERRO_NOME));
        assertTrue(pendencias.contains(Cpf.ERRO_CPF_INVALIDO));
    }

}