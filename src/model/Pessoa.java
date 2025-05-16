package model;

import java.time.LocalDate;

public abstract class Pessoa {
    protected static int contadorID=1;
    protected int id;
    protected String nome, cpf, telefone, email;
    protected LocalDate dataNascimento;

    public Pessoa(int id, String nome, String cpf, String telefone, String email, LocalDate dataNascimento) {
        this.id = contadorID++;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public int getId() {return id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getCpf() {return cpf;}
    public void setCpf(String cpf) {this.cpf = cpf;}

    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}
    
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public LocalDate getDataNascimento() {return dataNascimento;}
    public void setDataNascimento(LocalDate dataNascimento) {this.dataNascimento = dataNascimento;}

    @Override
    public String toString() {
    return "ID: " + id + 
           "\nNome: " + nome + 
           "\nCPF: " + cpf + 
           "\nTelefone: " + telefone;
    }
}
