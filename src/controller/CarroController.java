package controller;

import model.Carro;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarroController implements Gerenciavel<Carro> {
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

    
    @Override
    public void cadastrar(Carro carro) {
        carro.setId(nextId++);
        carros.add(carro);
    }

    @Override
    public void listar() {
        if (carros.isEmpty()) {
            System.out.println("Nenhum carro cadastrado.");
            return;
        }
        carros.forEach(System.out::println);
    }

    @Override
    public void atualizar(int id, Carro carroAtualizado) {
        buscarPorId(id).ifPresentOrElse(
            carroExistente -> {
                carroAtualizado.setId(id);
                carros.set(carros.indexOf(carroExistente), carroAtualizado);
            },
            () -> System.out.println("Carro não encontrado com ID: " + id)
        );
    }

    @Override
    public void remover(int id) {
        carros.removeIf(c -> c.getId() == id);
    }


    public List<Carro> listarCarros() {
        return new ArrayList<>(carros); 
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