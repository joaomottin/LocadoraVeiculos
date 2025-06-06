package controller;

import dal.MotoDAO;
import factory.MotoFactory;
import model.Moto;
import util.GeradorID;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MotoController implements Gerenciavel<Moto> {
    private List<Moto> motos;

    public MotoController() {
        try {
            motos = MotoDAO.carregar();
            System.out.println("Motos carregadas: " + motos.size());
            int maiorId = motos.stream()
                              .mapToInt(Moto::getId)
                              .max()
                              .orElse(0);
            GeradorID.setNextIdVeiculo(maiorId);
        } catch (IOException | ClassNotFoundException e) {
            motos = new ArrayList<>();
            System.out.println("Arquivo motos.ser não encontrado ou corrompido.");
        }
    }

    public static List<Moto> carregar() throws IOException, ClassNotFoundException {
        return MotoDAO.carregar();
    }

    private void salvar() {
        try {
            MotoDAO.salvar(motos);
        } catch (IOException e) {
            System.out.println("Erro ao salvar motos: " + e.getMessage());
        }
    }

    public Optional<Moto> buscarPorId(int id) {
        return motos.stream()
                    .filter(m -> m.getId() == id)
                    .findFirst();
    }

    public void cadastrarComFactory(String marca, String modelo, int anoFabricacao, String placa, double precoDiaria, boolean disponivel, int cilindradas, String tipoCarenagem) throws Exception {
        Moto moto = MotoFactory.criarMoto(marca, modelo, anoFabricacao, placa, precoDiaria, disponivel, cilindradas, tipoCarenagem);
        cadastrar(moto);
    }


    @Override
    public void cadastrar(Moto moto) {
        motos.add(moto);
        salvar();
    }

    @Override
    public void listar() {
        if (motos.isEmpty()) {
            System.out.println("Nenhuma moto cadastrada.");
            return;
        }
        motos.forEach(System.out::println);
    }

    @Override
    public void atualizar(int id, Moto motoAtualizada) {
        buscarPorId(id).ifPresentOrElse(
            motoExistente -> {
                motos.set(motos.indexOf(motoExistente), motoAtualizada);
                salvar();
            },
            () -> System.out.println("Moto não encontrada com ID: " + id)
        );
    }

    public void atualizar(int id, String marca, String modelo, int anoFabricacao, String placa,
                      double precoDiaria, int cilindradas, String tipoCarenagem) throws Exception {

    Optional<Moto> optMoto = buscarPorId(id);
    if (optMoto.isEmpty()) {
        throw new Exception("Moto não encontrada com ID: " + id);
    }

    MotoFactory.criarMoto(marca, modelo, anoFabricacao, placa, precoDiaria, true, cilindradas, tipoCarenagem);

    Moto moto = optMoto.get();
    moto.setMarca(marca);
    moto.setModelo(modelo);
    moto.setAnoFabricacao(anoFabricacao);
    moto.setPlaca(placa);
    moto.setPrecoDiaria(precoDiaria);
    moto.setCilindradas(cilindradas);
    moto.setTipoCarenagem(tipoCarenagem);

    salvar();
}


    @Override
    public void remover(int id) {
        boolean removido = motos.removeIf(m -> m.getId() == id);
        if (removido) salvar();
    }
}
