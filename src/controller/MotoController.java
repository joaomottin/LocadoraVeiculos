package controller;

import model.Moto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MotoController {
    private List<Moto> motos = new ArrayList<>();
    private int nextId = 1;

    public Moto adicionarMoto(String marca, String modelo, int anoFabricacao, String placa, double precoDiaria, boolean disponivel,
                             int cilindradas, String tipoCarenagem) {
        Moto moto = new Moto(nextId++, marca, modelo, anoFabricacao, placa, precoDiaria, disponivel, cilindradas, tipoCarenagem);
        motos.add(moto);
        return moto;
    }

    public List<Moto> listarMotos() {
        return motos;
    }

    public Optional<Moto> buscarPorId(int id) {
        return motos.stream().filter(m -> m.getId() == id).findFirst();
    }

    public boolean alugarMoto(int id) {
        Optional<Moto> motoOpt = buscarPorId(id);
        if (motoOpt.isPresent() && motoOpt.get().isDisponivel()) {
            motoOpt.get().setDisponivel(false);
            return true;
        }
        return false;
    }

    public boolean devolverMoto(int id) {
        Optional<Moto> motoOpt = buscarPorId(id);
        if (motoOpt.isPresent() && !motoOpt.get().isDisponivel()) {
            motoOpt.get().setDisponivel(true);
            return true;
        }
        return false;
    }
}
