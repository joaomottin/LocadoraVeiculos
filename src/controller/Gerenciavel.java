package controller;

public interface Gerenciavel<T> {
    void cadastrar(T obj);
    void listar();
    void atualizar(int id, T obj);
    void remover(int id);
}