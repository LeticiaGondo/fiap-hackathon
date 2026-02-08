package br.com.fiap.hackathon.tea.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MedicoTest {

    @Test
    void deveRetornarPendenciasQuandoCamposObrigatoriosNaoForemInformados() {
        Medico medico = new Medico("", "", "");

        List<String> pendencias = medico.validarPendencias();

        assertTrue(pendencias.contains(Medico.ERRO_NOME));
        assertTrue(pendencias.contains(Medico.ERRO_CRM_UF));
        assertTrue(pendencias.contains(Medico.ERRO_CRM_NUMERO));
    }

    @Test
    void deveRetornarSemPendenciasQuandoMedicoValido() {
        Medico medico = new Medico("Dr. João", "SP", "123456");
        assertTrue(medico.validarPendencias().isEmpty());
    }


}