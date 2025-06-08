package controller;

import dal.MotoDAO;
import factory.MotoFactory;
import model.Moto;
import util.GeradorID;
import util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MotoController implements Gerenciavel{
    private List<Moto> motos;

    public MotoController() {
        try {
            motos = MotoDAO.carregar();
            Log.carregar("Motos carregadas: " + motos.size());
            int maiorId = motos.stream()
                              .mapToInt(Moto::getId)
                              .max()
                              .orElse(0);
            GeradorID.setNextIdVeiculo(maiorId);
        } catch (IOException | ClassNotFoundException e) {
            motos = new ArrayList<>();
            throw new IllegalArgumentException("Arquivo motos.ser não encontrado ou corrompido.");
        }
    }

    public static List<Moto> carregar() throws IOException, ClassNotFoundException {
        return MotoDAO.carregar();
    }

    private void salvar() {
        try {
            MotoDAO.salvar(motos);
        } catch (IOException e) {
            throw new IllegalArgumentException("Erro ao salvar motos: " + e.getMessage());
        }
    }

    public Optional<Moto> buscarPorId(int id) {
        return motos.stream()
                    .filter(m -> m.getId() == id)
                    .findFirst();
    }

    public void cadastrar(String marca, String modelo, int anoFabricacao, String placa, double precoDiaria, boolean disponivel, int cilindradas, String tipoCarenagem) throws Exception {
        Moto moto = MotoFactory.criarMoto(marca, modelo, anoFabricacao, placa, precoDiaria, disponivel, cilindradas, tipoCarenagem);
        cadastrar(moto);
        Log.cadastrar("Moto cadastrado: " + moto.getMarca() + " " + moto.getModelo() + " (" + moto.getPlaca() + ")");
    }


    public void cadastrar(Moto moto) {
        motos.add(moto);
        salvar();
    }

    @Override
    public List<String> listar() {
        if (motos.isEmpty()) {
            throw new IllegalArgumentException("Nenhuma moto cadastrada.");
        }
        return motos.stream().map(Moto::toString).toList();
    }

    public void atualizar(int id, String marca, String modelo, int anoFabricacao, String placa, double precoDiaria, int cilindradas, String tipoCarenagem) throws Exception {

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
        Log.cadastrar("Moto atualizada: " + moto.getMarca() + " " + moto.getModelo() + " (" + moto.getPlaca() + ")");
    }

    @Override
    public void remover(int id) {
        Optional<Moto> motoOptional = buscarPorId(id);
        if (motoOptional.isPresent()) {
            Moto moto = motoOptional.get();
            motos.remove(moto);
            salvar();
            Log.excluir("Moto removida | ID = " + moto.getId());
        }
    }
}
