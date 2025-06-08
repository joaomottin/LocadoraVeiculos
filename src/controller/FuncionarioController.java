package controller;

import model.Aluguel;
import model.Funcionario;
import model.Turno;
import util.GeradorID;
import util.Log;
import dal.FuncionarioDAO;
import factory.FuncionarioFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class FuncionarioController {
    private List<Funcionario> funcionarios = new ArrayList<>();

    public FuncionarioController() {
        try {
            funcionarios = FuncionarioDAO.carregar();
            Log.carregar("Funcionarios carregados: " + funcionarios.size());
            int maiorId = funcionarios.stream()
                                      .mapToInt(Funcionario::getId)
                                      .max()
                                      .orElse(0);
            GeradorID.setNextIdPessoa(maiorId);
        } catch (IOException | ClassNotFoundException e) {
            funcionarios = new ArrayList<>();
            throw new IllegalArgumentException("Arquivo funcionarios.ser não encontrado ou corrompido.");
        }
    }

    public static List<Funcionario> carregar() throws IOException, ClassNotFoundException {
        return FuncionarioDAO.carregar();
    }

    private void salvar() {
        try {
            FuncionarioDAO.salvar(funcionarios);
        } catch (IOException e) {
            throw new IllegalArgumentException("Erro ao salvar funcionarios: " + e.getMessage());
        }
    }

    public Funcionario cadastrar(String nome, String cpf, String telefone, String email, LocalDate dataNascimento, Turno turno, String cargo, double salario, double comissao) throws Exception {
        Funcionario funcionario = FuncionarioFactory.criarFuncionario(nome, cpf, telefone, email, dataNascimento, turno, cargo, salario, comissao);
        funcionarios.add(funcionario);
        salvar();
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

    public String exibirSalarioEComissao(int idFuncionario, List<Aluguel> alugueis) {
        Optional<Funcionario> funcionarioOpt = buscarPorId(idFuncionario);
        if (funcionarioOpt.isEmpty()) {
            return "Funcionário não encontrado.";
        }
        Funcionario funcionario = funcionarioOpt.get();
        double totalComissao = alugueis.stream()
            .filter(a -> a.getFuncionario().getId() == funcionario.getId())
            .mapToDouble(Aluguel::getComissaoFuncionario)
            .sum();
        double salarioTotal = funcionario.getSalario() + totalComissao;
        return String.format("Funcionário: %s | Salário Base: R$ %.2f | Comissão total: R$ %.2f | Total: R$ %.2f",
            funcionario.getNome(), funcionario.getSalario(), totalComissao, salarioTotal);
    }   


    public boolean atualizarTurno(int idFuncionario, Turno novoTurno) {
        Optional<Funcionario> funcionarioOpt = buscarPorId(idFuncionario);
        if (funcionarioOpt.isPresent()) {
            funcionarioOpt.get().setTurno(novoTurno);
            salvar();
            return true;
        }
        return false;
    }

    public boolean atualizarSalario(int idFuncionario, double novoSalario) {
        Optional<Funcionario> funcionarioOpt = buscarPorId(idFuncionario);
        if (funcionarioOpt.isPresent()) {
            funcionarioOpt.get().setSalario(novoSalario);
            salvar();
            return true;
        }
        return false;
    }
}
