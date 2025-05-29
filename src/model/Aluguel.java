package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Aluguel implements Serializable {
    private int id;
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private double valorTotal;

    public Aluguel(int id, Cliente cliente, Veiculo veiculo, LocalDate dataInicio, LocalDate dataFim, double valorTotal) {
        this.id = id;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorTotal = valorTotal;
    }

    public Aluguel(Cliente cliente, Veiculo veiculo, LocalDate dataInicio, LocalDate dataFim, double valorTotal) {
        this(0, cliente, veiculo, dataInicio, dataFim, valorTotal); 
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    
    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Aluguel [ID: " + id + "] " + cliente.getNome() + 
               " alugou " + veiculo.getModelo() +
               " de " + dataInicio + " até " + dataFim + 
               " | Valor: R$ " + String.format("%.2f", valorTotal);
    }
}