
package controller;

import model.Cliente;
import util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClienteController implements Gerenciavel<Cliente> {
    private final List<Cliente> clientes = new ArrayList<>();
    private int ultimoId = 0;

    public Cliente cadastrar(String nome, String cpf) {
        try {
            Cliente cliente = new Cliente(++ultimoId, nome, cpf);
            clientes.add(cliente);
            Log.registrar("Cliente cadastrado: " + cliente);
            return cliente;
        } catch (Exception e) {
            Log.registrar("Erro ao cadastrar cliente: " + e.getMessage());
            return null;
        }
    }

    public Optional<Cliente> buscarPorCpf(String cpf) {
        return clientes.stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst();
    }

    @Override
    public List<Cliente> listar() {
        return clientes.stream().collect(Collectors.toList());
    }
}
