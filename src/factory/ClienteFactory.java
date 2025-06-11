package factory;

import model.Cliente;
import model.Veiculo;
import util.Validacao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteFactory {

    public static Cliente criarCliente(String nome, String cpf, String telefone, String email, LocalDate dataNascimento) {

        Validacao.validarStringVazia(nome, "Nome");
        Validacao.validarStringVazia(cpf, "CPF");
        Validacao.validarStringVazia(telefone, "Telefone");
        Validacao.validarStringVazia(email, "Email");
        Validacao.validarMaiorDeIdade(dataNascimento, "Data de nascimento");

        List<Veiculo> veiculosAlugados = new ArrayList<>();
        boolean possuiMulta = false;

        return new Cliente(nome, cpf, telefone, email, dataNascimento, veiculosAlugados, possuiMulta);
    }
}
