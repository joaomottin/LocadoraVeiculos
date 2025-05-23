package controller;

import model.Carro;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarroController {
    private List<Carro> carros = new ArrayList<>();
    private int nextId = 1;

    public Carro adicionarCarro(String marca, String modelo, int anoFabricacao,
                                String placa, double precoDiaria, boolean disponivel,
                                int numeroPortas, String tipoCombustivel) {
        Carro carro = new Carro(nextId++, marca, modelo, anoFabricacao, placa,
                                precoDiaria, disponivel, numeroPortas, tipoCombustivel);
        carros.add(carro);
        return carro;
    }

    public List<Carro> listarCarros() {
        return carros;
    }

    public Optional<Carro> buscarPorId(int id) {
        return carros.stream().filter(c -> c.getId() == id).findFirst();
    }

    public boolean alugarCarro(int id) {
        Optional<Carro> carroOpt = buscarPorId(id);
        if (carroOpt.isPresent() && carroOpt.get().isDisponivel()) {
            carroOpt.get().setDisponivel(false);
            return true;
        }
        return false;
    }

    public boolean devolverCarro(int id) {
        Optional<Carro> carroOpt = buscarPorId(id);
        if (carroOpt.isPresent() && !carroOpt.get().isDisponivel()) {
            carroOpt.get().setDisponivel(true);
            return true;
        }
        return false;
    }
}
