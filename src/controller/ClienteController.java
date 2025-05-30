package controller;

import model.Cliente;
import model.Veiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteController {
    private List<Cliente> clientes = new ArrayList<>();
    private int nextId = 1;

    public Cliente adicionarCliente(String nome, String cpf, String telefone, String email, LocalDate dataNascimento) {
        Cliente cliente = new Cliente(nextId++, nome, cpf, telefone, email, dataNascimento, new ArrayList<>(), false);
        clientes.add(cliente);
        return cliente;
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }

    public Optional<Cliente> buscarPorId(int id) {
        return clientes.stream().filter(c -> c.getId() == id).findFirst();
    }

    public boolean alugarVeiculo(int clienteId, Veiculo veiculo) {
        Optional<Cliente> clienteOpt = buscarPorId(clienteId);
        if (clienteOpt.isPresent() && veiculo.isDisponivel()) {
            Cliente cliente = clienteOpt.get();
            cliente.getVeiculosAlugados().add(veiculo);
            veiculo.setDisponivel(false);
            return true;
        }
        return false;
    }

    public boolean devolverVeiculo(int clienteId, Veiculo veiculo) {
        Optional<Cliente> clienteOpt = buscarPorId(clienteId);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            if (cliente.getVeiculosAlugados().remove(veiculo)) {
                veiculo.setDisponivel(true);
                return true;
            }
        }
        return false;
    }

    public boolean aplicarMulta(int clienteId) {
        Optional<Cliente> clienteOpt = buscarPorId(clienteId);
        if (clienteOpt.isPresent()) {
            clienteOpt.get().aplicarMulta();
            return true;
        }
        return false;
    }

    public boolean removerMulta(int clienteId) {
        Optional<Cliente> clienteOpt = buscarPorId(clienteId);
        if (clienteOpt.isPresent()) {
            clienteOpt.get().removerMulta();
            return true;
        }
        return false;
    }
}
