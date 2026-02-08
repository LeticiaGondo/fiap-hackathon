package br.com.fiap.hackathon.tea.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CidTest {

    @Test
    void deveValidarFamiliaTea() {
        assertTrue(Cid.ehCidTea("F84.0"));
        assertTrue(Cid.ehCidTea("f84.1"));
        assertFalse(Cid.ehCidTea("F90.0"));
    }

    @Test
    void deveRetornarPendenciaQuandoCidNaoForTea() {
        Cid cid = Cid.of("F90.0");
        assertTrue(cid.validarPendencias().contains(Cid.ERRO_CID_TEA));
    }

    @Test
    void deveRetornarPendenciaQuandoCidNaoInformadoSemNpe() {
        Cid cidNulo = Cid.of(null);
        Cid cidVazio = Cid.of("   ");

        assertNotNull(cidNulo);
        assertNotNull(cidVazio);
        assertTrue(cidNulo.validarPendencias().contains(Cid.ERRO_CID_OBRIGATORIO));
        assertTrue(cidVazio.validarPendencias().contains(Cid.ERRO_CID_OBRIGATORIO));
    }


    @Test
    void deveCriarCidUppercaseQuandoInformado() {
        Cid cid = Cid.of("f84.0");

        assertNotNull(cid);
        assertEquals("F84.0", cid.getCodigo());
    }

}