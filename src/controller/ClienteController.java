package controller;

import model.Cliente;
import model.Funcionario;
import model.Veiculo;
import util.GeradorID;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dal.ClienteDAO;
import factory.ClienteFactory;

public class ClienteController {
    private List<Cliente> clientes = new ArrayList<>();

    public ClienteController() {
        try {
            clientes = ClienteDAO.carregar();
            System.out.println("Funcionarios carregados: " + clientes.size());
            int maiorId = clientes.stream()
                              .mapToInt(Cliente::getId)
                              .max()
                              .orElse(0);
            GeradorID.setNextIdPessoa(maiorId);
        } catch (IOException | ClassNotFoundException e) {
            clientes = new ArrayList<>();
            System.out.println("Arquivo clientes.ser não encontrado ou corrompido.");
        }
    }

    public static List<Cliente> carregar() throws IOException, ClassNotFoundException {
        return ClienteDAO.carregar();
    }

    private void salvar() {
        try {
            ClienteDAO.salvar(clientes);
        } catch (IOException e) {
            System.out.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    public Cliente cadastrarComFactory(String nome, String cpf, String telefone, String email, LocalDate dataNascimento) throws Exception {
        Cliente cliente = ClienteFactory.criarCliente(nome, cpf, telefone, email, dataNascimento);
        clientes.add(cliente);
        salvar();
        return cliente;
    }


    public List<Cliente> listarClientes() {
        return clientes;
    }

    public Optional<Cliente> buscarPorId(int id) {
        return clientes.stream().filter(c -> c.getId() == id).findFirst();
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
