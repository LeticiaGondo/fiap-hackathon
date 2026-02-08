package br.com.fiap.hackathon.tea.domain;

import br.com.fiap.hackathon.tea.domain.exception.CpfInvalidoException;
import br.com.fiap.hackathon.tea.domain.exception.CpfObrigatorioException;

import java.util.ArrayList;
import java.util.List;

public class Cpf {

    public static final String ERRO_CPF_OBRIGATORIO = "CPF do paciente é obrigatório";
    public static final String ERRO_CPF_INVALIDO = "Cpf do paciente é inválido";

    private final String numero;

    private Cpf(String numero) {
        this.numero = numero;
    }

    public static Cpf of(String numero) {
        if (numero == null || numero.isBlank()) {
            return null;
        }
        return new Cpf(numero.replaceAll("\\D", ""));
    }

    public String getNumero() {
        return numero;
    }

    public List<String> validarPendencias() {
        List<String> pendencias = new ArrayList<>();

        if (!isValido(numero)) {
            pendencias.add(ERRO_CPF_INVALIDO);
        }
        return pendencias;
    }


    protected static boolean isValido(String cpf) {
        if (cpf.length() != 11) return false;
        if (cpf.chars().distinct().count() == 1) return false;

        int digito1 = calcularDigito(cpf.substring(0, 9), 10);
        int digito2 = calcularDigito(cpf.substring(0, 10), 11);

        return digito1 == Character.getNumericValue(cpf.charAt(9))
                && digito2 == Character.getNumericValue(cpf.charAt(10));
    }

    private static int calcularDigito(String base, int pesoInicial) {
        int soma = 0;
        int peso = pesoInicial;

        for (char c : base.toCharArray()) {
            soma += Character.getNumericValue(c) * peso--;
        }

        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }
}
