package controller;

import model.Moto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MotoController implements Gerenciavel<Moto> {
    private List<Moto> motos = new ArrayList<>();
    private int nextId = 1;

    
    @Override
    public void cadastrar(Moto moto) {
        moto.setId(nextId++);
        motos.add(moto);
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
            },
            () -> System.out.println("Moto não encontrada com ID: " + id)
        );
    }

    @Override
    public void remover(int id) {
        motos.removeIf(m -> m.getId() == id);
    }

   
    public Moto adicionarMoto(String marca, String modelo, int anoFabricacao, String placa, 
                            double precoDiaria, boolean disponivel,
                            int cilindradas, String tipoCarenagem) {
        Moto moto = new Moto(nextId++, marca, modelo, anoFabricacao, placa, 
                           precoDiaria, disponivel, cilindradas, tipoCarenagem);
        motos.add(moto);
        return moto;
    }

    public List<Moto> listarMotos() {
        return new ArrayList<>(motos); 
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