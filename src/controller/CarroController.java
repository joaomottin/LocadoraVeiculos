
package controller;

import model.Carro;
import util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CarroController implements Gerenciavel<Carro> {
    private final List<Carro> carros = new ArrayList<>();
    private int ultimoId = 0;

    public Carro cadastrar(String modelo, String placa, double precoDiaria, int portas) {
        try {
            Carro carro = new Carro(++ultimoId, modelo, placa, precoDiaria, portas);
            carros.add(carro);
            Log.registrar("Carro cadastrado: " + carro);
            return carro;
        } catch (Exception e) {
            Log.registrar("Erro ao cadastrar carro: " + e.getMessage());
            return null;
        }
    }

    public Optional<Carro> buscarPorPlaca(String placa) {
        return carros.stream()
                .filter(c -> c.getPlaca().equals(placa))
                .findFirst();
    }

    @Override
    public List<Carro> listar() {
        return carros.stream().collect(Collectors.toList());
    }
}
