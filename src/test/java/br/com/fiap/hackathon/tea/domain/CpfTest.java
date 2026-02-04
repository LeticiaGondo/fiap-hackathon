package br.com.fiap.hackathon.tea.domain;

import br.com.fiap.hackathon.tea.domain.encaminhamento.Cpf;
import br.com.fiap.hackathon.tea.domain.encaminhamento.exception.CpfInvalidoException;
import br.com.fiap.hackathon.tea.domain.encaminhamento.exception.CpfObrigatorioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CpfTest {

    @Test
    void deveLancarCpfObrigatorioException_quandoCpfForNulo() {
        assertThrows(CpfObrigatorioException.class, () -> new Cpf(null));
    }

    @Test
    void deveLancarCpfObrigatorioException_quandoCpfForVazio() {
        assertThrows(CpfObrigatorioException.class, () -> new Cpf("   "));
        assertThrows(CpfObrigatorioException.class, () -> new Cpf(""));
    }

    @Test
    void deveLancarCpfInvalidoException_quandoCpfTiverTamanhoInvalido() {
        assertThrows(CpfInvalidoException.class, () -> new Cpf("123"));
    }

    @Test
    void deveLancarCpfInvalidoException_quandoCpfTiverTodosDigitosIguais() {
        assertThrows(CpfInvalidoException.class, () -> new Cpf("111.111.111-11"));
        assertThrows(CpfInvalidoException.class, () -> new Cpf("00000000000"));
    }

    @Test
    void deveLancarCpfInvalidoException_quandoDigitosVerificadoresForemInvalidos() {
        // CPF válido base: 529.982.247-25
        // Aqui alteramos o último dígito pra ficar inválido:
        assertThrows(CpfInvalidoException.class, () -> new Cpf("529.982.247-26"));
    }

    @Test
    void deveCriarCpf_quandoCpfForValido_comMascara_eNormalizarNumero() {
        Cpf cpf = new Cpf("529.982.247-25");
        assertEquals("52998224725", cpf.getNumero());
    }

    @Test
    void deveCriarCpf_quandoCpfForValido_semMascara() {
        Cpf cpf = new Cpf("11144477735");
        assertEquals("11144477735", cpf.getNumero());
    }

    @Test
    void deveIgnorarCaracteresNaoNumericos_quandoCpfForValido() {
        Cpf cpf = new Cpf("529 982 247 25");
        assertEquals("52998224725", cpf.getNumero());
    }

}