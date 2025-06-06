package controller;

import dal.CarroDAO;
import factory.CarroFactory;
import model.Carro;
import util.GeradorID;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarroController implements Gerenciavel<Carro> {
    private List<Carro> carros;

    public CarroController() {
        try {
            carros = CarroDAO.carregar();
            System.out.println("Carros carregados: " + carros.size());
            int maiorId = carros.stream()
                              .mapToInt(Carro::getId)
                              .max()
                              .orElse(0);
            GeradorID.setNextIdVeiculo(maiorId);
        } catch (IOException | ClassNotFoundException e) {
            carros = new ArrayList<>();
            System.out.println("Arquivo carros.ser não encontrado ou corrompido.");
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

    public void cadastrarComFactory(String marca, String modelo, int anoFabricacao, String placa, double precoDiaria, boolean disponivel, int numeroPortas, String tipoCombustivel) throws Exception {
        Carro carro = CarroFactory.criarCarro(marca, modelo, anoFabricacao, placa, precoDiaria, disponivel, numeroPortas, tipoCombustivel);
        cadastrar(carro);
    }

    @Override
    public void cadastrar(Carro carro) {
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
                carros.set(carros.indexOf(carroExistente), carroAtualizado);
                salvar();
            },
            () -> System.out.println("Carro não encontrado com ID: " + id)
        );
    }

    public void atualizar(int id, String marca, String modelo, int anoFabricacao, String placa,
                          double precoDiaria, int numeroPortas, String tipoCombustivel) throws Exception {

        Optional<Carro> optCarro = buscarPorId(id);
        if (optCarro.isEmpty()) {
            throw new Exception("Carro não encontrado com ID: " + id);
        }

        CarroFactory.criarCarro(marca, modelo, anoFabricacao, placa, precoDiaria, true, numeroPortas, tipoCombustivel);

        Carro carro = optCarro.get();
        carro.setMarca(marca);
        carro.setModelo(modelo);
        carro.setAnoFabricacao(anoFabricacao);
        carro.setPlaca(placa);
        carro.setPrecoDiaria(precoDiaria);
        carro.setNumeroPortas(numeroPortas);
        carro.setTipoCombustivel(tipoCombustivel);

        salvar();
    }


    @Override
    public void remover(int id) {
        boolean removido = carros.removeIf(c -> c.getId() == id);
        if (removido) salvar();
    }
}
