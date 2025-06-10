package model;

public final class Moto extends Veiculo{
    private int cilindradas;
    private String tipoCarenagem;

    public Moto(String marca, String modelo, int anoFabricacao, String placa, double precoDiaria, boolean disponivel, int cilindradas, String tipoCarenagem) {
        super(marca, modelo, anoFabricacao, placa, precoDiaria, disponivel);
        this.cilindradas = cilindradas;
        this.tipoCarenagem = tipoCarenagem;
    }

    public int getCilindradas() {
        return cilindradas; 
    }
    public void setCilindradas(int cilindradas) { 
        this.cilindradas = cilindradas; 
    }
    public String getTipoCarenagem() {
        return tipoCarenagem; 
    }
    public void setTipoCarenagem(String tipoCarenagem) { 
        this.tipoCarenagem = tipoCarenagem; 
    }

    @Override
    public String tipoVeiculo() {
        return "Moto";
    } 
    @Override
    public String toString() {
        return super.toString() + " - Cilindradas: " + cilindradas + "cc - Tipo de carenagem: " + tipoCarenagem;
    }
}
