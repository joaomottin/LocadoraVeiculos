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

    public Carro adicionarCarro(String marca, String modelo, int anoFabricacao, String placa, double precoDiaria, boolean disponivel, int numeroPortas, String tipoCombustivel) {
        int id = GeradorID.getNextId();  
        Carro carro = new Carro(id, marca, modelo, anoFabricacao, placa, precoDiaria, disponivel, numeroPortas, tipoCombustivel);
        carros.add(carro);
        salvar();
        return carro;
    }

    public List<Carro> listarCarros() {
        return new ArrayList<>(carros);
    }

    public Optional<Carro> buscarPorId(int id) {
        return carros.stream()
                     .filter(c -> c.getId() == id)
                     .findFirst();
    }

    public boolean alugarCarro(int id) {
        Optional<Carro> carroOpt = buscarPorId(id);
        if (carroOpt.isPresent() && carroOpt.get().isDisponivel()) {
            carroOpt.get().setDisponivel(false);
            salvar();
            return true;
        }
        return false;
    }

    public boolean devolverCarro(int id) {
        Optional<Carro> carroOpt = buscarPorId(id);
        if (carroOpt.isPresent() && !carroOpt.get().isDisponivel()) {
            carroOpt.get().setDisponivel(true);
            salvar();
            return true;
        }
        return false;
    }

    @Override
    public void cadastrar(Carro carro) {
        carro.setId(nextId++);
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
