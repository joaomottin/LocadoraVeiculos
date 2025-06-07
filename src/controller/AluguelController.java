package controller;

import model.Aluguel;
import model.Cliente;
import model.Funcionario;
import model.Veiculo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AluguelController implements Gerenciavel {
    private List<Aluguel> alugueis = new ArrayList<>();
    private int ultimoId = 0;
    
    public boolean alugarVeiculo(Cliente cliente, Veiculo veiculo, Funcionario funcionario, LocalDate inicio, LocalDate fim) {
        if (!veiculo.isDisponivel()) return false;
        if (clienteTemAluguelAtivo(cliente)) return false;
        
        long dias = ChronoUnit.DAYS.between(inicio, fim);
        double valor = dias * veiculo.getPrecoDiaria();
        
        Aluguel aluguel = new Aluguel(++ultimoId, cliente, veiculo, inicio, fim, valor, funcionario);
        alugueis.add(aluguel);
        veiculo.setDisponivel(false);
        cliente.getVeiculosAlugados().add(veiculo);
        return true;
    }

    public boolean clienteTemAluguelAtivo(Cliente cliente) {
        return alugueis.stream()
                       .anyMatch(a -> a.getCliente().equals(cliente) && !a.getVeiculo().isDisponivel());
        }

    
    public boolean devolverVeiculo(Veiculo veiculo) {
        if (veiculo.isDisponivel()) return false;
        veiculo.setDisponivel(true);
        return true;
    }
    
    public List<Aluguel> listarTodos() {
        return new ArrayList<>(alugueis);
    }
    
    public List<Aluguel> listarAtivos() {
        return alugueis.stream()
                       .filter(a -> !a.getVeiculo().isDisponivel())
                       .collect(Collectors.toList());
    }
    
    public List<Aluguel> buscarPorCliente(String nome) {
        return alugueis.stream()
                       .filter(a -> a.getCliente().getNome().equalsIgnoreCase(nome))
                       .collect(Collectors.toList());
    }
    
    public String exibirSalarioEComissao(Funcionario funcionario) {
        double totalComissao = alugueis.stream()
                                       .filter(a -> a.getFuncionario().equals(funcionario))
                                       .mapToDouble(a -> a.getValorTotal() * funcionario.calcularComissao(ultimoId))
                                       .sum();
        double salarioTotal = funcionario.getSalario() + totalComissao;

        return String.format("Funcionário: %s | Salário Base: R$ %.2f | Comissão: R$ %.2f | Total: R$ %.2f",
                funcionario.getNome(), funcionario.getSalario(), totalComissao, salarioTotal);
    }   


    public void cadastrar(Aluguel aluguel) {
        throw new UnsupportedOperationException("Use o método alugarVeiculo para cadastrar corretamente.");
    }
    
    @Override
    public List<String> listar() {
        if (alugueis.isEmpty()) {
            throw new IllegalArgumentException("Nenhum aluguel registrado.");
        }
        return alugueis.stream().map(Aluguel::toString).toList();
    }
    
    public void atualizar(int id, Aluguel novoAluguel) {
        for (int i = 0; i < alugueis.size(); i++) {
            if (alugueis.get(i).getId() == id) {
                Aluguel atualizado = new Aluguel(
                    id,
                    novoAluguel.getCliente(),
                    novoAluguel.getVeiculo(),
                    novoAluguel.getDataInicio(),
                    novoAluguel.getDataFim(),
                    novoAluguel.getValorTotal(),
                    novoAluguel.getFuncionario()
                );
                alugueis.set(i, atualizado);
                return;
            }
        }
        throw new IllegalArgumentException("Aluguel não encontrado para o ID: " + id);
    }

    @Override
    public void remover(int id) {
        Aluguel aluguel = alugueis.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);

        if (aluguel != null) {
            aluguel.getVeiculo().setDisponivel(true);
            alugueis.remove(aluguel);
        } else {
            throw new IllegalArgumentException("Aluguel não encontrado para o ID: " + id);
        }
    }
}

