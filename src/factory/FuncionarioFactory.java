package factory;

import exception.StringVaziaException;
import model.Funcionario;
import model.Turno;

import java.time.LocalDate;
import java.time.Period;

public class FuncionarioFactory {

    public static Funcionario criarFuncionario(String nome, String cpf, String telefone, String email, LocalDate dataNascimento, Turno turno, String cargo, double salario, double comissao) throws Exception {

        if (nome == null || nome.trim().isEmpty()) {
            throw new StringVaziaException("Nome não pode ser vazio");
        }

        if (cpf == null || cpf.trim().isEmpty()) {
            throw new StringVaziaException("CPF não pode ser vazio");
        }

        if (telefone == null || telefone.trim().isEmpty()) {
            throw new StringVaziaException("Telefone não pode ser vazio");
        }

        if (email == null || email.trim().isEmpty()) {
            throw new StringVaziaException("Email não pode ser vazio");
        }

        if (dataNascimento == null || dataNascimento.isAfter(LocalDate.now()) ||
                Period.between(dataNascimento, LocalDate.now()).getYears() < 18) {
            throw new IllegalArgumentException("Funcionário deve ter pelo menos 18 anos");
        }

        if (turno == null) {
            throw new IllegalArgumentException("Turno deve ser informado");
        }

        if (cargo == null || cargo.trim().isEmpty()) {
            throw new StringVaziaException("Cargo não pode ser vazio");
        }

        if (salario < 0) {
            throw new IllegalArgumentException("Salário não pode ser negativo");
        }

        if (comissao < 0) {
            throw new IllegalArgumentException("Comissão não pode ser negativa");
        }

        return new Funcionario(nome, cpf, telefone, email, dataNascimento, turno, cargo, salario, comissao);
    }
}
