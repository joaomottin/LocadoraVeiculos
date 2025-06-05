package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Aluguel implements Serializable {
    private int id;
    private Cliente cliente;
    private Veiculo veiculo;
    private Funcionario funcionario;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private double valorTotal;

    public Aluguel(int id, Cliente cliente, Veiculo veiculo, LocalDate dataInicio, LocalDate dataFim, double valorTotal, Funcionario funcionario) {
    this.id = id;
    this.cliente = cliente;
    this.veiculo = veiculo;
    this.dataInicio = dataInicio;
    this.dataFim = dataFim;
    this.valorTotal = valorTotal;
    this.funcionario = funcionario;
    }

    public Aluguel(Cliente cliente, Veiculo veiculo, LocalDate dataInicio, LocalDate dataFim, double valorTotal, Funcionario funcionario) {
        this(0, cliente, veiculo, dataInicio, dataFim, valorTotal, funcionario);
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
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
        return "Aluguel [ID: " + id + "] " + cliente.getNome() + " alugou " + veiculo.getModelo() + " de " + dataInicio + " até " + dataFim + " | Valor: R$ " + String.format("%.2f", valorTotal) + " | Funcionário: " + funcionario.getNome();}
}