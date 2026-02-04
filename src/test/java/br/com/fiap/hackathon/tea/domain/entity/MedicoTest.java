package br.com.fiap.hackathon.tea.domain.entity;

import br.com.fiap.hackathon.tea.domain.Medico;
import br.com.fiap.hackathon.tea.domain.exception.ValidacaoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicoTest {

    @Test
    void deveCriarMedico_quandoDadosForemValidos() {
        Medico medico = new Medico("Dr. João", "SP", "123456");

        assertNotNull(medico);
    }

    @Test
    void deveLancarException_quandoNomeForNulo() {
        ValidacaoException ex = assertThrows(
                ValidacaoException.class,
                () -> new Medico(null, "SP", "123456")
        );

        assertEquals("Nome do médico é obrigatório", ex.getMessage());
    }

    @Test
    void deveLancarException_quandoNomeForVazio() {
        ValidacaoException ex1 = assertThrows(
                ValidacaoException.class,
                () -> new Medico("", "SP", "123456")
        );

        assertEquals("Nome do médico é obrigatório", ex1.getMessage());

        ValidacaoException ex2 = assertThrows(
                ValidacaoException.class,
                () -> new Medico("   ", "SP", "123456")
        );

        assertEquals("Nome do médico é obrigatório", ex2.getMessage());
    }

    @Test
    void deveLancarException_quandoCrmUfForNulo() {
        ValidacaoException ex = assertThrows(
                ValidacaoException.class,
                () -> new Medico("Dr. João", null, "123456")
        );

        assertEquals("UF do CRM é obrigatória", ex.getMessage());
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
    }



    @Test
    void deveLancarException_quandoCrmNumeroForNulo() {
        ValidacaoException ex = assertThrows(
                ValidacaoException.class,
                () -> new Medico("Dr. João", "SP", null)
        );

        assertEquals("Número do CRM é obrigatório", ex.getMessage());
    }

    @Test
    void deveLancarException_quandoCrmNumeroForVazio() {

        ValidacaoException ex1 = assertThrows(
                ValidacaoException.class,
                () -> new Medico("Dr. João", "SP", "   ")
        );

        assertEquals("Número do CRM é obrigatório", ex1.getMessage());

        ValidacaoException ex2 = assertThrows(
                ValidacaoException.class,
                () -> new Medico("Dr. João", "SP", "   ")
        );

        assertEquals("Número do CRM é obrigatório", ex2.getMessage());
    }

}