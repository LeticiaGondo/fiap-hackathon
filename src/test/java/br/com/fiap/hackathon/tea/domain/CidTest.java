package br.com.fiap.hackathon.tea.domain;

import br.com.fiap.hackathon.tea.domain.exception.ValidacaoException;
import org.junit.jupiter.api.Test;

import static br.com.fiap.hackathon.tea.domain.Cid.ERRO_CID_OBRIGATORIO;
import static br.com.fiap.hackathon.tea.domain.Cid.ERRO_CID_TEA;
import static org.junit.jupiter.api.Assertions.*;

class CidTest {

    @Test
    void deveCriarCid_quandoForTea() {
        assertDoesNotThrow(() -> new Cid("F84.0"));
    }

    @Test
    void deveLancarException_quandoCidNaoForTea() {
        ValidacaoException ex =  assertThrows(ValidacaoException.class, () -> new Cid("F90.0"));
        assertEquals(ERRO_CID_TEA, ex.getMessage());
    }

    @Test
    void deveLancarException_quandoCidForNulo() {
        ValidacaoException ex =  assertThrows(ValidacaoException.class, () -> new Cid(null));
        assertEquals(ERRO_CID_OBRIGATORIO, ex.getMessage());
    }

    @Test
    void deveLancarException_quandoCidForVazio() {
        ValidacaoException ex =  assertThrows(ValidacaoException.class, () -> new Cid(""));
        assertEquals(ERRO_CID_OBRIGATORIO, ex.getMessage());

        ValidacaoException ex1 =  assertThrows(ValidacaoException.class, () -> new Cid("  "));
        assertEquals(ERRO_CID_OBRIGATORIO, ex1.getMessage());
    }

}