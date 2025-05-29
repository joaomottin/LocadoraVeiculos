
package controller;

import exception.VeiculoIndisponivelException;
import model.Aluguel;
import model.Cliente;
import model.Veiculo;
import util.Log;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AluguelController implements Gerenciavel<Aluguel> {
    private List<Aluguel> alugueis = new ArrayList<>();
    private int ultimoId = 0;

    public boolean alugarVeiculo(Cliente cliente, Veiculo veiculo, LocalDate inicio, LocalDate fim) throws VeiculoIndisponivelException {
        if (!veiculo.isDisponivel()) {
            throw new VeiculoIndisponivelException("Veículo não está disponível para aluguel.");
        }

        try {
            long dias = fim.toEpochDay() - inicio.toEpochDay();
            double valor = dias * veiculo.getPrecoDiaria();

            Aluguel aluguel = new Aluguel(++ultimoId, cliente, veiculo, inicio, fim, valor);
            alugueis.add(aluguel);
            veiculo.setDisponivel(false);
            Log.registrar("Alugado: " + aluguel);
            return true;
        } catch (Exception e) {
            Log.registrar("Erro ao alugar veículo: " + e.getMessage());
            return false;
        }
    }

    public boolean devolverVeiculo(Veiculo veiculo) {
        Optional<Aluguel> aluguelOptional = alugueis.stream()
                .filter(a -> a.getVeiculo().equals(veiculo) && !a.getVeiculo().isDisponivel())
                .findFirst();

        if (aluguelOptional.isPresent()) {
            veiculo.setDisponivel(true);
            Log.registrar("Devolvido: " + aluguelOptional.get());
            return true;
        }
        return false;
    }

    @Override
    public List<Aluguel> listar() {
        return new ArrayList<>(alugueis);
    }
}
