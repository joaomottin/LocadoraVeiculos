package controller;

import model.Funcionario;
import model.Turno;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FuncionarioController {
    private List<Funcionario> funcionarios = new ArrayList<>();
    private int nextId = 1;

    public Funcionario adicionarFuncionario(String nome, String cpf, String telefone, String email,
                                            LocalDate dataNascimento, Turno turno, String cargo,
                                            double salario, double comissao) {
        Funcionario funcionario = new Funcionario(nextId++, nome, cpf, telefone, email, dataNascimento,
                                                  turno, cargo, salario, comissao);
        funcionarios.add(funcionario);
        return funcionario;
    }

    public List<Funcionario> listarFuncionarios() {
        return funcionarios;
    }

    public Optional<Funcionario> buscarPorId(int id) {
        return funcionarios.stream().filter(f -> f.getId() == id).findFirst();
    }

    public Optional<Double> calcularComissao(int idFuncionario, double valorVenda) {
        Optional<Funcionario> funcionarioOpt = buscarPorId(idFuncionario);
        return funcionarioOpt.map(f -> f.calcularComissao(valorVenda));
    }

    public boolean atualizarTurno(int idFuncionario, Turno novoTurno) {
        Optional<Funcionario> funcionarioOpt = buscarPorId(idFuncionario);
        if (funcionarioOpt.isPresent()) {
            funcionarioOpt.get().setTurno(novoTurno);
            return true;
        }
        return false;
    }

    public boolean atualizarSalario(int idFuncionario, double novoSalario) {
        Optional<Funcionario> funcionarioOpt = buscarPorId(idFuncionario);
        if (funcionarioOpt.isPresent()) {
            funcionarioOpt.get().setSalario(novoSalario);
            return true;
        }
        return false;
    }
}
