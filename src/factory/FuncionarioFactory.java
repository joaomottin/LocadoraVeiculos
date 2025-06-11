package factory;

import model.Funcionario;
import model.Turno;
import util.Validacao;

import java.time.LocalDate;

public class FuncionarioFactory {

    public static Funcionario criarFuncionario(String nome, String cpf, String telefone, String email, LocalDate dataNascimento, Turno turno, String cargo, double salario, double comissao) {

        Validacao.validarStringVazia(nome, "Nome");
        Validacao.validarStringVazia(cpf, "CPF");
        Validacao.validarStringVazia(telefone, "Telefone");
        Validacao.validarStringVazia(email, "Email");
        Validacao.validarMaiorDeIdade(dataNascimento, "Data de nascimento");
        Validacao.validarNaoNulo(turno, "Turno");
        Validacao.validarStringVazia(cargo, "Cargo");
        Validacao.validarNumeroPositivo(salario, "Salário");
        Validacao.validarNumeroPositivo(comissao, "Comissão");
        Validacao.validarNumeroEntre(comissao, 0, 20, "Comissão");

        return new Funcionario(nome, cpf, telefone, email, dataNascimento, turno, cargo, salario, comissao);
    }
}
