package controller;

import dal.MotoDAO;
import model.Moto;
import util.GeradorID;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MotoController implements Gerenciavel<Moto> {
    private List<Moto> motos;
    private int nextId = 1;

    public MotoController() {
        try {
            motos = MotoDAO.carregar();
            System.out.println("Motos carregadas: " + motos.size());
            int maiorId = motos.stream()
                              .mapToInt(Moto::getId)
                              .max()
                              .orElse(0);
            GeradorID.setNextId(maiorId);
        } catch (IOException | ClassNotFoundException e) {
            motos = new ArrayList<>();
            System.out.println("Arquivo motos.ser não encontrado ou corrompido.");
            nextId = 1;
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

    @Override
    public void cadastrar(Moto moto) {
        moto.setId(GeradorID.getNextId());
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
                motoAtualizada.setId(id);
                motos.set(motos.indexOf(motoExistente), motoAtualizada);
                salvar();
            },
            () -> System.out.println("Moto não encontrada com ID: " + id)
        );
    }

    @Override
    public void remover(int id) {
        boolean removido = motos.removeIf(m -> m.getId() == id);
        if (removido) salvar();
    }
}
