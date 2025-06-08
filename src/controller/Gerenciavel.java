package controller;

import java.util.List;

public interface Gerenciavel {
    List<String> listar();
    void remover(int id);
}  