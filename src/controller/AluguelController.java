package controller;

import model.Aluguel;
import model.Cliente;
import model.Funcionario;
import model.Veiculo;
import util.Log;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AluguelController implements Gerenciavel<Aluguel> {
    private List<Aluguel> alugueis = new ArrayList<>();
    private int ultimoId = 0;
    
    public boolean alugarVeiculo(Cliente cliente, Veiculo veiculo, Funcionario funcionario, LocalDate inicio, LocalDate fim) {
        if (!veiculo.isDisponivel()) return false;
        
        long dias = ChronoUnit.DAYS.between(inicio, fim);
        double valor = dias * veiculo.getPrecoDiaria();
        
        Aluguel aluguel = new Aluguel(++ultimoId, cliente, veiculo, inicio, fim, valor, funcionario);
        alugueis.add(aluguel);
        veiculo.setDisponivel(false);
        Log.registrar("Alugado: " + aluguel);
        return true;
    }
    
    public boolean devolverVeiculo(Veiculo veiculo) {
        if (veiculo.isDisponivel()) return false;
        veiculo.setDisponivel(true);
        Log.registrar("Devolvido: " + veiculo.getModelo());
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
    
    public void exibirSalarioEComissao(Funcionario funcionario) {
        double totalComissao = alugueis.stream()
                .filter(a -> a.getFuncionario().equals(funcionario))
                .mapToDouble(a -> a.getValorTotal() * 0.05)
                .sum();
        double salarioTotal = funcionario.getSalario() + totalComissao;
        System.out.printf("Funcionário: %s | Salário Base: R$ %.2f | Comissão: R$ %.2f | Total: R$ %.2f%n",
                funcionario.getNome(), funcionario.getSalario(), totalComissao, salarioTotal);
    }
    @Override
    public void cadastrar(Aluguel aluguel) {
        throw new UnsupportedOperationException("Use o método alugarVeiculo para cadastrar corretamente.");
    }
    
    @Override
    public void listar() {
        if (alugueis.isEmpty()) {
            System.out.println("Nenhum aluguel registrado.");
            return;
        }
        alugueis.forEach(System.out::println);
    }
    
    @Override
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
                Log.registrar("Aluguel atualizado: " + atualizado);
                return;
            }
        }
        System.out.println("Aluguel não encontrado para o ID: " + id);
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
            Log.registrar("Aluguel removido: " + aluguel);
        } else {
            System.out.println("Aluguel não encontrado para o ID: " + id);
        }
    }
}

