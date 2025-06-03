package controller;

import dal.CarroDAO;
import model.Carro;
import util.GeradorID;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarroController implements Gerenciavel<Carro> {
    private List<Carro> carros;
    private int nextId = 1;

    public CarroController() {
        try {
            carros = CarroDAO.carregar();
            System.out.println("Carros carregados: " + carros.size());
            int maiorId = carros.stream()
                              .mapToInt(Carro::getId)
                              .max()
                              .orElse(0);
            GeradorID.setNextId(maiorId);
        } catch (IOException | ClassNotFoundException e) {
            carros = new ArrayList<>();
            System.out.println("Arquivo carros.ser não encontrado ou corrompido.");
            nextId = 1;
        }
    }

    public static List<Carro> carregar() throws IOException, ClassNotFoundException {
        return CarroDAO.carregar();
    }

    private void salvar() {
        try {
            CarroDAO.salvar(carros);
        } catch (IOException e) {
            System.out.println("Erro ao salvar carros: " + e.getMessage());
        }
    }

    public Optional<Carro> buscarPorId(int id) {
        return carros.stream()
                     .filter(c -> c.getId() == id)
                     .findFirst();
    }

    @Override
    public void cadastrar(Carro carro) {
        carro.setId(GeradorID.getNextId());
        carros.add(carro);
        salvar();
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
                salvar();
            },
            () -> System.out.println("Carro não encontrado com ID: " + id)
        );
    }

    @Override
    public void remover(int id) {
        boolean removido = carros.removeIf(c -> c.getId() == id);
        if (removido) salvar();
    }
}
