package model;

import java.time.LocalDate;

public final class Funcionario extends Pessoa{
    private Turno turno;
    private String cargo;
    private double salario, comissao, comissaoAcumulada;

    public Funcionario(String nome, String cpf, String telefone, String email, LocalDate dataNascimento,
            Turno turno, String cargo, double salario, double comissao) {
        super(nome, cpf, telefone, email, dataNascimento);
        this.turno = turno;
        this.cargo = cargo;
        this.salario = salario;
        this.comissao = comissao;
    }

    public double calcularComissao(double valorVenda) {
        return valorVenda * (comissao / 100.0);
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    public double getComissao() {
        return comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    public double getComissaoAcumulada() {
        return comissaoAcumulada;
    }
    
    public void setComissaoAcumulada(double comissaoAcumulada) {
        this.comissaoAcumulada = comissaoAcumulada;
    }

    @Override
    public String toString() {
        return "ID = " + getId() + " | " + getNome() + " - " + cargo + " (" + turno + ") - Salário R$" + String.format("%.2f", salario) + " - Comissão " + getComissao() + "%";
    }
}
