package view;

import java.time.LocalDate;
import java.time.Period;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import controller.AluguelController;
import controller.CarroController;
import controller.ClienteController;
import controller.FuncionarioController;
import controller.MotoController;
import model.Aluguel;
import model.Carro;
import model.Cliente;
import model.Funcionario;
import model.Moto;
import model.Veiculo;

public class AluguelView {
    private final AluguelController aluguelController;
    private final ClienteController clienteController;
    private final MotoController motoController;
    private final CarroController carroController;
    private final FuncionarioController funcionarioController;
    private final Scanner sc;

    public AluguelView(AluguelController aluguelController, ClienteController clienteController, MotoController motoController, CarroController carroController, FuncionarioController funcionarioController, Scanner sc) {
        this.aluguelController = aluguelController;
        this.clienteController = clienteController;
        this.motoController = motoController;
        this.carroController = carroController;
        this.funcionarioController = funcionarioController;
        this.sc = sc;
    }

    public void menu() {
        int opcao = -1;
        do {
            System.out.println("\n--- Menu Aluguel ---");
            System.out.println("1. Mostrar aluguéis ativos");
            System.out.println("2. Listar todos os aluguéis");
            System.out.println("3. Adicionar aluguel");
            System.out.println("4. Alterar aluguel");
            System.out.println("5. Remover aluguel");
            System.out.println("6. Mostrar salário e comissão dos funcionários");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                    opcao = sc.nextInt();
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida! Por favor, digite um número inteiro.");
                    sc.nextLine();
                    opcao = -1;
                }
        
            switch (opcao) {
                case 1 -> {
                    List<Aluguel> ativos = aluguelController.listarAtivos();
                    if (ativos.isEmpty()) {
                        System.out.println("Nenhum aluguel ativo.");
                    } else {
                        ativos.forEach(System.out::println);
                    }
                }
                case 2 -> {
                    List<Aluguel> todos = aluguelController.listarTodos();
                    if (todos.isEmpty()) {
                        System.out.println("Nenhum aluguel cadastrado.");
                    } else {
                        System.out.println("--- Todos os Aluguéis ---");
                        todos.forEach(System.out::println);
                    }
                }
                case 3 -> {
                    List<Cliente> clientes = clienteController.listarClientes();
                    if (clientes.isEmpty()) {
                        System.out.println("\nNenhum cliente cadastrado.");
                    } else {
                        for (Cliente c : clientes) {
                            System.out.println(c);
                        }
                    }
                    System.out.print("\nID do cliente: ");
                    int clienteId = sc.nextInt();
                    sc.nextLine();
                    Optional<Cliente> clienteOpt = clienteController.buscarPorId(clienteId);
                    if (clienteOpt.isEmpty()) {
                        System.out.println("Cliente não encontrado.");
                        break;
                    }
                    Cliente cliente = clienteOpt.get();
                
                    System.out.print("Tipo de veículo (moto/carro): ");
                    String tipoVeiculo = sc.nextLine().toLowerCase();
                
                    Veiculo veiculo = null;
                    if (tipoVeiculo.equals("moto")) {
                        motoController.listar().forEach(System.out::println);
                        System.out.print("\nID da moto para alugar: ");
                        int motoId = sc.nextInt();
                        sc.nextLine();
                        Optional<Moto> motoOpt = motoController.buscarPorId(motoId);
                        if (motoOpt.isEmpty()) {
                            System.out.println("Moto não encontrada.");
                            break;
                        }
                        veiculo = motoOpt.get();
                    } else if (tipoVeiculo.equals("carro")) {
                        carroController.listar().forEach(System.out::println);
                        System.out.print("\nID do carro para alugar: ");
                        int carroId = sc.nextInt();
                        sc.nextLine();
                        Optional<Carro> carroOpt = carroController.buscarPorId(carroId);
                        if (carroOpt.isEmpty()) {
                            System.out.println("Carro não encontrado.");
                            break;
                        }
                        veiculo = carroOpt.get();
                    } else {
                        System.out.println("Tipo de veículo inválido.");
                        break;
                    }
                
                    funcionarioController.listarFuncionarios().forEach(System.out::println);
                    System.out.print("\nID do funcionário responsável: \n");
                    int funcionarioId = sc.nextInt();
                    sc.nextLine();
                    Optional<Funcionario> funcionarioOpt = funcionarioController.buscarPorId(funcionarioId);
                    if (funcionarioOpt.isEmpty()) {
                        System.out.println("Funcionário não encontrado.");
                        break;
                    }
                    Funcionario funcionario = funcionarioOpt.get();
                
                    System.out.print("Data início (AAAA-MM-DD): ");
                    LocalDate dataInicio = LocalDate.parse(sc.nextLine());
                    System.out.print("Data fim (AAAA-MM-DD): ");
                    LocalDate dataFim = LocalDate.parse(sc.nextLine());
                
                    boolean sucesso = aluguelController.alugarVeiculo(cliente, veiculo, funcionario, dataInicio, dataFim);
                    if (sucesso) {
                        clienteController.salvar();
                        System.out.println("\nAluguel cadastrado com sucesso!");
                    } else {
                        System.out.println("Falha ao cadastrar aluguel (veículo pode estar indisponível).");
                    }
                }
                case 4 -> {
                    System.out.print("ID do aluguel a alterar: ");
                    int aluguelId = sc.nextInt();
                    sc.nextLine();
                    Optional<Aluguel> aluguelOpt = aluguelController.listarTodos().stream()
                            .filter(a -> a.getId() == aluguelId)
                            .findFirst();
                    if (aluguelOpt.isEmpty()) {
                        System.out.println("Aluguel não encontrado.");
                        break;
                    }
                    Aluguel aluguel = aluguelOpt.get();
                
                    System.out.print("Novo tipo de veículo (moto/carro): ");
                    String tipoVeiculo = sc.nextLine().toLowerCase();
                
                    Veiculo novoVeiculo = null;
                    if (tipoVeiculo.equals("moto")) {
                        motoController.listar();
                        System.out.print("ID da nova moto: ");
                        int motoId = sc.nextInt();
                        sc.nextLine();
                        Optional<Moto> motoOpt = motoController.buscarPorId(motoId);
                        if (motoOpt.isEmpty()) {
                            System.out.println("Moto não encontrada.");
                            break;
                        }
                        novoVeiculo = motoOpt.get();
                    } else if (tipoVeiculo.equals("carro")) {
                        carroController.listar();
                        System.out.print("ID do novo carro: ");
                        int carroId = sc.nextInt();
                        sc.nextLine();
                        Optional<Carro> carroOpt = carroController.buscarPorId(carroId);
                        if (carroOpt.isEmpty()) {
                            System.out.println("Carro não encontrado.");
                            break;
                        }
                        novoVeiculo = carroOpt.get();
                    } else {
                        System.out.println("Tipo de veículo inválido.");
                        break;
                    }
                
                    if (!novoVeiculo.isDisponivel()) {
                        System.out.println("Veículo indisponível.");
                        break;
                    }
                
                    aluguel.getVeiculo().setDisponivel(true);
                    aluguel.setVeiculo(novoVeiculo);
                    novoVeiculo.setDisponivel(false);
                
                    Period periodo = Period.between(aluguel.getDataInicio(), aluguel.getDataFim());
                    int dias = periodo.getDays() + (periodo.getMonths() * 30) + (periodo.getYears() * 365);
                    double novoValor = dias * novoVeiculo.getPrecoDiaria();
                    aluguel.setValorTotal(novoValor);
                    System.out.println("Aluguel atualizado com novo veículo com sucesso!");
                }
                case 5 -> {
                    System.out.print("ID do aluguel a remover: ");
                    int aluguelId = sc.nextInt();
                    sc.nextLine();
                    aluguelController.remover(aluguelId);
                    System.out.println("Aluguel removido (se encontrado).");
                }
                case 6 -> {
                    List<Funcionario> funcionarios = funcionarioController.listarFuncionarios();
                    if (funcionarios.isEmpty()) {
                        System.out.println("Nenhum funcionário cadastrado.");
                    } else {
                        System.out.println("--- Funcionários e suas remunerações ---");
                        for (Funcionario f : funcionarios) {
                            String resultado = funcionarioController.exibirSalarioEComissao(f.getId(), aluguelController.listarTodos());
                            System.out.println(resultado);
                        }
                    }
                }
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}
