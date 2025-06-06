package factory;

import exception.StringVaziaException;
import model.Cliente;
import model.Veiculo;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class ClienteFactory {

    public static Cliente criarCliente(String nome, String cpf, String telefone, String email, LocalDate dataNascimento ) throws Exception {
        
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

        if (dataNascimento == null || dataNascimento.isAfter(LocalDate.now()) || Period.between(dataNascimento, LocalDate.now()).getYears() < 18) {
            throw new IllegalArgumentException("Data de nascimento inválida");
        }

        List<Veiculo> veiculosAlugados = new ArrayList<>();
        boolean possuiMulta = false;

        return new Cliente(nome, cpf, telefone, email, dataNascimento, veiculosAlugados, possuiMulta);
    }
}
