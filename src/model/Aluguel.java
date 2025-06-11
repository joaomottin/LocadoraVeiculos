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
    private double comissaoFuncionario;

    public Aluguel(int id, Cliente cliente, Veiculo veiculo, LocalDate dataInicio, LocalDate dataFim, double valorTotal, Funcionario funcionario) {
        this.id = id;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorTotal = valorTotal;
        this.funcionario = funcionario;
        atualizarComissao();
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
        atualizarComissao();
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
        atualizarComissao();
    }

    public double getComissaoFuncionario() {
        return comissaoFuncionario;
    }

    private void atualizarComissao() {
        if (funcionario != null) {
            this.comissaoFuncionario = funcionario.calcularComissao(this.valorTotal);
        } else {
            this.comissaoFuncionario = 0.0;
        }
    }
    
    @Override
    public String toString() {
        return "Aluguel [ID: " + id + "] " + cliente.getNome() + " alugou " + veiculo.getModelo() + 
               " de " + dataInicio + " até " + dataFim + " | Valor: R$ " + String.format("%.2f", valorTotal) + 
               " | Funcionário: " + funcionario.getNome();
    }
}
