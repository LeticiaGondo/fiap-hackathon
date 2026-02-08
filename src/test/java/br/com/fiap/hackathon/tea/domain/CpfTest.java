package br.com.fiap.hackathon.tea.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CpfTest {

    @Test
    void deveRetornarFalsoParaCpfNuloOuVazio() {
        assertFalse(Cpf.isValido(null));
        assertFalse(Cpf.isValido("   "));
    }

    @Test
    void deveRetornarFalsoParaCpfInvalido() {
        assertFalse(Cpf.isValido("123"));
        assertFalse(Cpf.isValido("111.111.111-11"));
        assertFalse(Cpf.isValido("529.982.247-26"));
    }

    @Test
    void deveCriarCpfNormalizadoQuandoValido() {
        Cpf cpf = Cpf.of("529.982.247-25");
        assertNotNull(cpf);
        assertEquals("52998224725", cpf.getNumero());
        assertTrue(cpf.validarPendencias().isEmpty());
    }



}