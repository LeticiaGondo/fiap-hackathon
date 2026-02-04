package br.com.fiap.hackathon.validator.domain.entity;

import br.com.fiap.hackathon.validator.domain.exception.ValidacaoDominioException;
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
        ValidacaoDominioException ex = assertThrows(
                ValidacaoDominioException.class,
                () -> new Medico(null, "SP", "123456")
        );

        assertEquals("Nome do médico é obrigatório", ex.getMessage());
    }

    @Test
    void deveLancarException_quandoNomeForVazio() {
        ValidacaoDominioException ex1 = assertThrows(
                ValidacaoDominioException.class,
                () -> new Medico("", "SP", "123456")
        );

        assertEquals("Nome do médico é obrigatório", ex1.getMessage());

        ValidacaoDominioException ex2 = assertThrows(
                ValidacaoDominioException.class,
                () -> new Medico("   ", "SP", "123456")
        );

        assertEquals("Nome do médico é obrigatório", ex2.getMessage());
    }

    @Test
    void deveLancarException_quandoCrmUfForNulo() {
        ValidacaoDominioException ex = assertThrows(
                ValidacaoDominioException.class,
                () -> new Medico("Dr. João", null, "123456")
        );

        assertEquals("UF do CRM é obrigatória", ex.getMessage());
    }

    @Test
    void deveLancarException_quandoCrmUfForVazio() {
        ValidacaoDominioException ex1 = assertThrows(
                ValidacaoDominioException.class,
                () -> new Medico("Dr. João", "", "123456")
        );

        assertEquals("UF do CRM é obrigatória", ex1.getMessage());

        ValidacaoDominioException ex2 = assertThrows(
                ValidacaoDominioException.class,
                () -> new Medico("Dr. João", "   ", "123456")
        );

        assertEquals("UF do CRM é obrigatória", ex2.getMessage());
    }



    @Test
    void deveLancarException_quandoCrmNumeroForNulo() {
        ValidacaoDominioException ex = assertThrows(
                ValidacaoDominioException.class,
                () -> new Medico("Dr. João", "SP", null)
        );

        assertEquals("Número do CRM é obrigatório", ex.getMessage());
    }

    @Test
    void deveLancarException_quandoCrmNumeroForVazio() {

        ValidacaoDominioException ex1 = assertThrows(
                ValidacaoDominioException.class,
                () -> new Medico("Dr. João", "SP", "   ")
        );

        assertEquals("Número do CRM é obrigatório", ex1.getMessage());

        ValidacaoDominioException ex2 = assertThrows(
                ValidacaoDominioException.class,
                () -> new Medico("Dr. João", "SP", "   ")
        );

        assertEquals("Número do CRM é obrigatório", ex2.getMessage());
    }

}