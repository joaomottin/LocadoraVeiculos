package model;

public final class Carro extends Veiculo {
    private int numeroPortas;
    private String tipoCombustivel;

    public Carro(int id, String marca, String modelo, int anoFabricacao, String placa,
                 double precoDiaria, boolean disponivel,
                 int numeroPortas, String tipoCombustivel) {
        super(id, marca, modelo, anoFabricacao, placa, precoDiaria, disponivel);
        this.numeroPortas = numeroPortas;
        this.tipoCombustivel = tipoCombustivel;
    }

    public int getNumeroPortas() { return numeroPortas; }
    public void setNumeroPortas(int numeroPortas) { this.numeroPortas = numeroPortas; }

    public String getTipoCombustivel() { return tipoCombustivel; }
    public void setTipoCombustivel(String tipoCombustivel) { this.tipoCombustivel = tipoCombustivel; }

    @Override
    public String tipoVeiculo() {
        return "Carro";
    }

    @Override
    public String toString() {
        return super.toString() + " - Portas: " + numeroPortas + " - Combustível: " + tipoCombustivel;
    }
}
