package controller;

import dal.AluguelDAO;
import model.Aluguel;
import model.Cliente;
import model.Funcionario;
import model.Veiculo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AluguelController implements Gerenciavel {
    private List<Aluguel> alugueis = new ArrayList<>();
    private int ultimoId = 0;

    public AluguelController() {
        try {
            alugueis = AluguelDAO.carregar();
            ultimoId = alugueis.stream()
                               .mapToInt(Aluguel::getId)
                               .max()
                               .orElse(0);
            atualizarDisponibilidadeVeiculos();
        } catch (IOException | ClassNotFoundException e) {
            alugueis = new ArrayList<>();
            ultimoId = 0;
        }
    }

    private void atualizarDisponibilidadeVeiculos() {
        LocalDate hoje = LocalDate.now();
        alugueis.forEach(a -> {
            Veiculo v = a.getVeiculo();
            if (!hoje.isBefore(a.getDataInicio()) && !hoje.isAfter(a.getDataFim())) {
                v.setDisponivel(false);
            } else {
                v.setDisponivel(true);
            }
        });
    }

    private void salvar() {
        try {
            AluguelDAO.salvar(alugueis);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar aluguéis: " + e.getMessage());
        }
    }

    public boolean alugarVeiculo(Cliente cliente, Veiculo veiculo, Funcionario funcionario, LocalDate inicio, LocalDate fim) {
        if (!veiculo.isDisponivel() || clienteTemAluguelAtivo(cliente)) return false;

        Period periodo = Period.between(inicio, fim);
        int dias = periodo.getDays() + (periodo.getMonths() * 30) + (periodo.getYears() * 365);

        double valor = dias * veiculo.getPrecoDiaria();

        if (cliente.isPossuiMulta()) {
        valor *= 1.10;
        }

        Aluguel aluguel = new Aluguel(++ultimoId, cliente, veiculo, inicio, fim, valor, funcionario);
        alugueis.add(aluguel);

        atualizarDisponibilidadeVeiculos();
        cliente.getVeiculosAlugados().add(veiculo);
        salvar();
        return true;
    }

    public boolean clienteTemAluguelAtivo(Cliente cliente) {
        LocalDate hoje = LocalDate.now();
        return alugueis.stream()
                       .anyMatch(a -> a.getCliente().equals(cliente)
                            && !hoje.isBefore(a.getDataInicio())
                            && !hoje.isAfter(a.getDataFim()));
    }

    public List<Aluguel> listarAtivos() {
        LocalDate hoje = LocalDate.now();
        return alugueis.stream()
                       .filter(a -> !hoje.isBefore(a.getDataInicio()) && !hoje.isAfter(a.getDataFim()))
                       .collect(Collectors.toList());
    }

    public List<Aluguel> listarTodos() {
        return new ArrayList<>(alugueis);
    }

    public List<Aluguel> buscarPorCliente(String nome) {
        return alugueis.stream()
                       .filter(a -> a.getCliente().getNome().equalsIgnoreCase(nome))
                       .collect(Collectors.toList());
    }

    public Optional<Aluguel> buscarPorId(int id) {
        return alugueis.stream().filter(a -> a.getId() == id).findFirst();
    }

    public void atualizar(int id, Aluguel novoAluguel) {
        for (int i = 0; i < alugueis.size(); i++) {
            if (alugueis.get(i).getId() == id) {
                Aluguel atualizado = new Aluguel(
                    id,
                    novoAluguel.getCliente(),
                    novoAluguel.getVeiculo(),
                    novoAluguel.getDataInicio(),
                    novoAluguel.getDataFim(),
                    novoAluguel.getValorTotal(),
                    novoAluguel.getFuncionario()
                );
                alugueis.set(i, atualizado);
                salvar();
                return;
            }
        }
        throw new IllegalArgumentException("Aluguel não encontrado para o ID: " + id);
    }

    @Override
    public void remover(int id) {
        Optional<Aluguel> aluguelOpt = buscarPorId(id);

        if (aluguelOpt.isPresent()) {
            Aluguel aluguel = aluguelOpt.get();
            aluguel.getVeiculo().setDisponivel(true);
            alugueis.remove(aluguel);
            salvar();
        } else {
            throw new IllegalArgumentException("Aluguel não encontrado para o ID: " + id);
        }
    }

    @Override
    public List<String> listar() {
        if (alugueis.isEmpty()) {
            throw new IllegalArgumentException("Nenhum aluguel registrado.");
        }
        return alugueis.stream().map(Aluguel::toString).collect(Collectors.toList());
    }
}
