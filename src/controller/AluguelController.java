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

public class AluguelController {
    private List<Aluguel> alugueis = new ArrayList<>();

    public boolean alugarVeiculo(Cliente cliente, Veiculo veiculo, LocalDate inicio, LocalDate fim) {
        if (!veiculo.isDisponivel()) return false;

        long dias = ChronoUnit.DAYS.between(inicio, fim);
        double valor = dias * veiculo.getPrecoDiaria();

        Aluguel aluguel = new Aluguel(cliente, veiculo, inicio, fim, valor);
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
        return alugueis;
    }

    public List<Aluguel> buscarPorCliente(String nome) {
        return alugueis.stream()
                .filter(a -> a.getCliente().getNome().equalsIgnoreCase(nome))
                .collect(Collectors.toList());
    }
} 
