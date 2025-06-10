package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class Cliente extends Pessoa{
    private List<Veiculo> veiculosAlugados;
    private boolean possuiMulta;
    
    public Cliente(String nome, String cpf, String telefone, String email, LocalDate dataNascimento,
            List<Veiculo> veiculosAlugados, boolean possuiMulta) {
        super(nome, cpf, telefone, email, dataNascimento);
        this.veiculosAlugados = veiculosAlugados != null ? veiculosAlugados : new ArrayList<>();
        this.possuiMulta = possuiMulta;
    }

    public List<Veiculo> getVeiculosAlugados() {
        return veiculosAlugados;
    }
    public void setVeiculosAlugados(List<Veiculo> veiculosAlugados) {
        this.veiculosAlugados = veiculosAlugados;
    }
    public boolean isPossuiMulta() {
        return possuiMulta;
    }
    public void aplicarMulta() {
        this.possuiMulta = true;
    }
    public void removerMulta() {
        this.possuiMulta = false;
    }
    
    @Override
    public String toString() {
        return "ID = " + getId() + " | " + getNome() + " - Multa: " + (possuiMulta ? "Sim" : "Não") + " - Veículos alugados: " + (veiculosAlugados != null ? veiculosAlugados.size() : 0);
    }
}
