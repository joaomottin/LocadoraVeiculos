package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public final class Cliente extends Pessoa implements Serializable{
    private List<Veiculo> veiculosAlugados;
    private boolean possuiMulta;
    
    public Cliente(int id, String nome, String cpf, String telefone, String email, LocalDate dataNascimento,
            List<Veiculo> veiculosAlugados, boolean possuiMulta) {
        super(id, nome, cpf, telefone, email, dataNascimento);
        this.veiculosAlugados = veiculosAlugados;
        this.possuiMulta = false;
    }

    public List<Veiculo> getVeiculosAlugados() {
        return veiculosAlugados;
    }
    public void setVeiculosAlugados(List<Veiculo> veiculosAlugados) {
        this.veiculosAlugados = veiculosAlugados;
    }
    
    public boolean isPossuiMulta() {return possuiMulta;}

    public void aplicarMulta() {
        this.possuiMulta = true;
    }
    public void removerMulta() {
        this.possuiMulta = false;
    }


    @Override
    public String toString() {
        return getNome() + " - Multa: " + (possuiMulta ? "Sim" : "Não") 
               + " - Veículos alugados: " + (veiculosAlugados != null ? veiculosAlugados.size() : 0);
    }
}
