package controller;

import model.Aluguel;
import model.Cliente;
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

    
    public boolean alugarVeiculo(Cliente cliente, Veiculo veiculo, LocalDate inicio, LocalDate fim) {
        if (!veiculo.isDisponivel()) return false;

        long dias = ChronoUnit.DAYS.between(inicio, fim);
        double valor = dias * veiculo.getPrecoDiaria();

        Aluguel aluguel = new Aluguel(++ultimoId, cliente, veiculo, inicio, fim, valor);
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

    
    @Override
    public void cadastrar(Aluguel aluguel) {
        aluguel.setId(++ultimoId);
        alugueis.add(aluguel);
        Log.registrar("Aluguel cadastrado: " + aluguel);
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
                novoAluguel.setId(id);
                alugueis.set(i, novoAluguel);
                Log.registrar("Aluguel atualizado: " + novoAluguel);
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
            alugueis.remove(aluguel);
            Log.registrar("Aluguel removido: " + aluguel);
        } else {
            System.out.println("Aluguel não encontrado para o ID: " + id);
        }
    }

    
    public List<Aluguel> listarTodos() {
        return new ArrayList<>(alugueis); 
    }

    public List<Aluguel> buscarPorCliente(String nome) {
        return alugueis.stream()
                .filter(a -> a.getCliente().getNome().equalsIgnoreCase(nome))
                .collect(Collectors.toList());
    }
}