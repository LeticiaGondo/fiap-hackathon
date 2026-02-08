package br.com.fiap.hackathon.tea.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CpfTest {

    @Test
    void deveRetornarPendenciaQuandoCpfForInvalido() {
        Cpf cpf = Cpf.of("123");
        assertTrue(cpf.validarPendencias().contains(Cpf.ERRO_CPF_INVALIDO));

    }

    @Test
    void deveRetornarPendenciaQuandoCpfNaoInformadoSemNpe() {
        Cpf cpfNulo = Cpf.of(null);
        Cpf cpfVazio = Cpf.of("   ");

        assertNotNull(cpfNulo);
        assertNotNull(cpfVazio);
        assertTrue(cpfNulo.validarPendencias().contains(Cpf.ERRO_CPF_OBRIGATORIO));
        assertTrue(cpfVazio.validarPendencias().contains(Cpf.ERRO_CPF_OBRIGATORIO));
    }

    @Test
    void deveCriarCpfNormalizadoQuandoValido() {
        Cpf cpf = Cpf.of("529.982.247-25");
        assertNotNull(cpf);
        assertEquals("52998224725", cpf.getNumero());
        assertTrue(cpf.validarPendencias().isEmpty());
    }



}