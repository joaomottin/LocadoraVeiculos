package model;

public final class Carro extends Veiculo{
    private static final long serialVersionUID = 1L;
    private int numeroPortas;
    private String tipoCombustivel;

    public Carro(String marca, String modelo, int anoFabricacao, String placa, double precoDiaria, boolean disponivel, int numeroPortas, String tipoCombustivel) {
        super(marca, modelo, anoFabricacao, placa, precoDiaria, disponivel);
        this.numeroPortas = numeroPortas;
        this.tipoCombustivel = tipoCombustivel;
    }

    public int getNumeroPortas() {
        return numeroPortas; 
    }

    public void setNumeroPortas(int numeroPortas) {
        this.numeroPortas = numeroPortas;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel; 
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel; 
    }

    @Override
    public String tipoVeiculo() {
        return "Carro";
    }
    
    @Override
    public String toString() {
        return "ID: " + getId() + " - Marca: " + getMarca() + " - Modelo: " + getModelo() + " - Ano: " + getAnoFabricacao() + " - Placa: " + getPlaca() + " - Preço/Diária: R$" + getPrecoDiaria() + " - Disponível: " + (isDisponivel() ? "Sim" : "Não") + " - Portas: " + numeroPortas + " - Combustível: " + tipoCombustivel;
    }
}
