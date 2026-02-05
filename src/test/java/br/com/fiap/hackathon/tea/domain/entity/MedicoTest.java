package br.com.fiap.hackathon.tea.domain.entity;

import br.com.fiap.hackathon.tea.domain.Medico;
import br.com.fiap.hackathon.tea.domain.exception.ValidacaoException;
import org.junit.jupiter.api.Test;

import static br.com.fiap.hackathon.tea.domain.Medico.*;
import static org.junit.jupiter.api.Assertions.*;

class MedicoTest {

    @Test
    void deveCriarMedico_quandoDadosForemValidos() {
        Medico medico = new Medico("Dr. João", "SP", "123456");

        assertNotNull(medico);
    }

    @Test
    void deveLancarException_quandoCampoObrigatorioForNulo() {
        ValidacaoException nomeEx = assertThrows(
                ValidacaoException.class,
                () -> new Medico(null, "SP", "123456")
        );
        assertEquals(ERRO_NOME, nomeEx.getMessage());

        ValidacaoException ufEx = assertThrows(
                ValidacaoException.class,
                () -> new Medico("Dr. João", null, "123456")
        );
        assertEquals(ERRO_CRM_UF, ufEx.getMessage());

        ValidacaoException numeroEx = assertThrows(
                ValidacaoException.class,
                () -> new Medico("Dr. João", "SP", null)
        );
        assertEquals(ERRO_CRM_NUMERO, numeroEx.getMessage());
    }

    @Test
    void deveLancarException_quandoCampoObrigatorioForVazio() {
        ValidacaoException exNome = assertThrows(
                ValidacaoException.class,
                () -> new Medico("", "SP", "123456")
        );
        assertEquals(ERRO_NOME, exNome.getMessage());

        ValidacaoException exUf = assertThrows(
                ValidacaoException.class,
                () -> new Medico("Dr. João", " ", "123456")
        );
        assertEquals(ERRO_CRM_UF, exUf.getMessage());


    }

    @Test
    void deveLancarException_quandoCrmUfForVazio() {
        ValidacaoException ex1 = assertThrows(
                ValidacaoException.class,
                () -> new Medico("Dr. João", "", "123456")
        );
        assertEquals("UF do CRM é obrigatória", ex1.getMessage());

        ValidacaoException ex2 = assertThrows(
                ValidacaoException.class,
                () -> new Medico("Dr. João", "   ", "123456")
        );
        assertEquals("UF do CRM é obrigatória", ex2.getMessage());

        ValidacaoException numeroEx = assertThrows(
                ValidacaoException.class,
                () -> new Medico("Dr. João", "SP", "   ")
        );
        assertEquals(ERRO_CRM_NUMERO, numeroEx.getMessage());
    }


}