package model;

import java.time.LocalDate;

public final class Funcionario extends Pessoa{
    private Turno turno;
    private String cargo;
    private double salario, comissao;

    public Funcionario(int id, String nome, String cpf, String telefone, String email, LocalDate dataNascimento,
            Turno turno, String cargo, double salario, double comissao) {
        super(id, nome, cpf, telefone, email, dataNascimento);
        this.turno = turno;
        this.cargo = cargo;
        this.salario = salario;
        this.comissao = comissao;
    }

    public double calcularComissao(double valorVenda) {
        return valorVenda * comissao;
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

    @Override
    public String toString() {
        return getNome() + " - " + cargo + " (" + turno + ")";
    }
}
