package util;

import java.time.LocalDate;
import java.time.Period;

public class Validacao {

    public static class StringVaziaException extends RuntimeException {
        public StringVaziaException(String msg) {
            super(msg);
        }
    }

    public static class NumeroInvalidoException extends RuntimeException {
        public NumeroInvalidoException(String msg) {
            super(msg);
        }
    }

    public static class ValorNuloException extends RuntimeException {
        public ValorNuloException(String msg) {
            super(msg);
        }
    }

    public static void validarStringVazia(String valor, String nomeCampo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new StringVaziaException(nomeCampo + " não pode ser vazio.");
        }
    }

    public static void validarNaoNulo(Object obj, String nomeCampo) {
        if (obj == null) {
            throw new ValorNuloException(nomeCampo + " não pode ser nulo.");
        }
    }

    public static void validarNumeroPositivo(double valor, String nomeCampo) {
        if (valor < 0) {
            throw new NumeroInvalidoException(nomeCampo + " deve ser positivo.");
        }
    }

    public static void validarNumeroEntre(double comissao, int min, int max, String nomeCampo) {
        if (comissao < min || comissao > max) {
            throw new NumeroInvalidoException(nomeCampo + " deve estar entre " + min + " e " + max + ".");
        }
    }

    public static void validarMaiorDeIdade(LocalDate dataNascimento, String nomeCampo) {
        validarNaoNulo(dataNascimento, nomeCampo);
        if (dataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(nomeCampo + " não pode ser uma data futura.");
        }
        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();
        if (idade < 18) {
            throw new IllegalArgumentException(nomeCampo + " indica que o cliente tem menos de 18 anos.");
        }
    }
}
