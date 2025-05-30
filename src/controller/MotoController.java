
package controller;

import model.Moto;
import util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MotoController implements Gerenciavel<Moto> {
    private final List<Moto> motos = new ArrayList<>();
    private int ultimoId = 0;

    public Moto cadastrar(String modelo, String placa, double precoDiaria, boolean bau) {
        try {
            Moto moto = new Moto(++ultimoId, modelo, placa, precoDiaria, bau);
            motos.add(moto);
            Log.registrar("Moto cadastrada: " + moto);
            return moto;
        } catch (Exception e) {
            Log.registrar("Erro ao cadastrar moto: " + e.getMessage());
            return null;
        }
    }

    public Optional<Moto> buscarPorPlaca(String placa) {
        return motos.stream()
                .filter(m -> m.getPlaca().equals(placa))
                .findFirst();
    }

    @Override
    public List<Moto> listar() {
        return motos.stream().collect(Collectors.toList());
    }
}
