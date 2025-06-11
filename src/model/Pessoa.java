package model;

import java.io.Serializable;
import java.time.LocalDate;

import util.GeradorID;

public abstract class Pessoa implements Serializable{
    private final int id;
    private String nome, cpf, telefone, email;
    private LocalDate dataNascimento;

    public Pessoa(String nome, String cpf, String telefone, String email, LocalDate dataNascimento) {
        this.id = GeradorID.getNextIdPessoa();
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
    return "ID: " + id + "\nNome: " + nome + "\nCPF: " + cpf + "\nTelefone: " + telefone;
    }
}
