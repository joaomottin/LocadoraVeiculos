package model;

import java.io.Serializable;

import util.GeradorID;

public abstract class Veiculo implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String marca;
    private String modelo;
    private int anoFabricacao;
    private String placa;
    private double precoDiaria;
    private boolean disponivel;

    public Veiculo(String marca, String modelo, int anoFabricacao, String placa, double precoDiaria, boolean disponivel) {
        this.id = GeradorID.getNextIdVeiculo();
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.placa = placa;
        this.precoDiaria = precoDiaria;
        this.disponivel = disponivel;
    }

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca; 
    }
    public void setMarca(String marca) {
        this.marca = marca; 
    }

    public String getModelo() {
        return modelo; 
    }
    public void setModelo(String modelo) {
        this.modelo = modelo; 
    }

    public int getAnoFabricacao() {
        return anoFabricacao; 
    }
    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao; 
    }

    public String getPlaca() {
        return placa; 
    }
    public void setPlaca(String placa) {
        this.placa = placa; 
    }

    public double getPrecoDiaria() {
        return precoDiaria; 
    }
    public void setPrecoDiaria(double precoDiaria) {
        this.precoDiaria = precoDiaria; 
    }

    public boolean isDisponivel() {
        return disponivel; 
    }
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel; 
    }

    public abstract String tipoVeiculo();

    @Override
    public String toString() {
        return "ID: " + id + " - " + marca + " " + modelo + " (" + anoFabricacao + ") - Placa: " + placa + " - R$" + precoDiaria + "/dia - " + (disponivel ? "Disponível" : "Alugado");
    }

}
