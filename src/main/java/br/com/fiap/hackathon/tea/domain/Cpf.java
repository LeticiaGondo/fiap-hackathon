package br.com.fiap.hackathon.tea.domain;

import br.com.fiap.hackathon.tea.domain.exception.CpfInvalidoException;
import br.com.fiap.hackathon.tea.domain.exception.CpfObrigatorioException;

public class Cpf {

    public String getNumero() {
        return numero;
    }

    private final String numero;

    public Cpf(String numero) {
        if (numero == null || numero.isBlank()) {
            throw new CpfObrigatorioException();
        }

        String cpfNumerico = numero.replaceAll("\\D", "");

        if (!isValido(cpfNumerico)) {
            throw new CpfInvalidoException("CPF inválido");
        }

        this.numero = cpfNumerico;
    }


    private boolean isValido(String cpf) {
        if (cpf.length() != 11) return false;
        if (cpf.chars().distinct().count() == 1) return false;

        int digito1 = calcularDigito(cpf.substring(0, 9), 10);
        int digito2 = calcularDigito(cpf.substring(0, 10), 11);

        return digito1 == Character.getNumericValue(cpf.charAt(9)) &&
                digito2 == Character.getNumericValue(cpf.charAt(10));
    }

    private int calcularDigito(String base, int pesoInicial) {
        int soma = 0;
        int peso = pesoInicial;

        for (char c : base.toCharArray()) {
            soma += Character.getNumericValue(c) * peso--;
        }

        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }
}
