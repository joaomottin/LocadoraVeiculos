package view;

import controller.*;
import model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class LocadoraView {
    private static final Scanner sc = new Scanner(System.in);
    private static final MotoController motoController = new MotoController();
    private static final CarroController carroController = new CarroController();
    private static final ClienteController clienteController = new ClienteController();
    private static final FuncionarioController funcionarioController = new FuncionarioController();
    private static final AluguelController aluguelController = new AluguelController();

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n====== LOCADORA DE VEÍCULOS ======");
            System.out.println("1. Gerenciar Motos");
            System.out.println("2. Gerenciar Carros");
            System.out.println("3. Gerenciar Clientes");
            System.out.println("4. Gerenciar Funcionários");
            System.out.println("5. Alugar Veículo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> menuMotos();
                case 2 -> menuCarros();
                case 3 -> menuClientes();
                case 4 -> menuFuncionarios();
                case 5 -> menuAluguel();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void menuMotos() {
        int opcao;
        do {
            System.out.println("\n--- Menu Motos ---");
            System.out.println("1. Listar motos");
            System.out.println("2. Adicionar moto");
            System.out.println("3. Modificar moto");
            System.out.println("4. Remover moto");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.println("\n--- Lista de Motos ---");
                    motoController.listar();
                }
                case 2 -> {
                    System.out.print("Marca: ");
                    String marca = sc.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = sc.nextLine();
                    System.out.print("Ano de fabricação: ");
                    int ano = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Placa: ");
                    String placa = sc.nextLine();
                    System.out.print("Preço diária: ");
                    double preco = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Cilindradas: ");
                    int cilindradas = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Tipo de carenagem: ");
                    String carenagem = sc.nextLine();

                    Moto novaMoto = new Moto(marca, modelo, ano, placa, preco, true, cilindradas, carenagem);
                    motoController.cadastrar(novaMoto);
                    System.out.println("Moto adicionada com sucesso!");
                }
                case 3 -> { 
                    System.out.print("ID da moto a modificar: ");
                    int idMod = sc.nextInt();
                    sc.nextLine();

                    Optional<Moto> optMoto = motoController.buscarPorId(idMod);
                    if (optMoto.isEmpty()) {
                        System.out.println("Moto não encontrada com ID: " + idMod);
                    } else {
                        System.out.print("Nova marca: ");
                        String marcaAtualizada = sc.nextLine();
                        System.out.print("Novo modelo: ");
                        String modeloAtualizado = sc.nextLine();
                        System.out.print("Novo ano de fabricação: ");
                        int anoAtualizado = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Nova placa: ");
                        String placaAtualizada = sc.nextLine();
                        System.out.print("Novo preço diária: ");
                        double precoAtualizado = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Novas cilindradas: ");
                        int cilindradasAtualizado = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Novo tipo de carenagem: ");
                        String carenagemAtualizado = sc.nextLine();

                        Moto motoAtualizada = new Moto(marcaAtualizada, modeloAtualizado, anoAtualizado, placaAtualizada, precoAtualizado, true, cilindradasAtualizado, carenagemAtualizado);
                        motoController.atualizar(idMod, motoAtualizada);
                        System.out.println("Moto atualizada com sucesso!");
                    }
                }
                case 4 -> {
                    System.out.print("ID da moto a remover: ");
                    int idRem = sc.nextInt();
                    sc.nextLine();

                    Optional<Moto> optMotoRem = motoController.buscarPorId(idRem);
                    if (optMotoRem.isEmpty()) {
                        System.out.println("Moto não encontrada com ID: " + idRem);
                    } else {
                        motoController.remover(idRem);
                        System.out.println("Moto removida com sucesso!");
                    }
                }
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }


    private static void menuCarros() {
        int opcao;
        do {
            System.out.println("\n--- Menu Carros ---");
            System.out.println("1. Listar carros");
            System.out.println("2. Adicionar carro");
            System.out.println("3. Modificar carro");
            System.out.println("4. Remover carro");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.println("\n--- Lista de Carros ---");
                    carroController.listar();
                }
                case 2 -> {
                    System.out.print("Marca: ");
                    String marca = sc.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = sc.nextLine();
                    System.out.print("Ano de fabricação: ");
                    int ano = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Placa: ");
                    String placa = sc.nextLine();
                    System.out.print("Preço diária: ");
                    double preco = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Número de portas: ");
                    int portas = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Tipo de combustível: ");
                    String combustivel = sc.nextLine();

                    Carro novoCarro = new Carro(marca, modelo, ano, placa, preco, true, portas, combustivel);
                    carroController.cadastrar(novoCarro);
                    System.out.println("Carro adicionado com sucesso!");
                }
                case 3 -> {
                    System.out.print("ID do carro a modificar: ");
                    int idMod = sc.nextInt();
                    sc.nextLine();

                    Optional<Carro> optCarro = carroController.buscarPorId(idMod);
                    if (optCarro.isEmpty()) {
                        System.out.println("Carro não encontrado com ID: " + idMod);
                    } else {
                        System.out.print("Nova marca: ");
                        String marcaAtualizada = sc.nextLine();
                        System.out.print("Novo modelo: ");
                        String modeloAtualizado = sc.nextLine();
                        System.out.print("Novo ano de fabricação: ");
                        int anoAtualizado = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Nova placa: ");
                        String placaAtualizada = sc.nextLine();
                        System.out.print("Novo preço diária: ");
                        double precoAtualizado = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Novo número de portas: ");
                        int portasAtualizado = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Novo tipo de combustível: ");
                        String combustivelAtualizado = sc.nextLine();

                        Carro carroAtualizado = new Carro(marcaAtualizada, modeloAtualizado, anoAtualizado, placaAtualizada, precoAtualizado, true, portasAtualizado, combustivelAtualizado);
                        carroController.atualizar(idMod, carroAtualizado);
                        System.out.println("Carro atualizado com sucesso!");
                    }
                }
                case 4 -> {
                    System.out.print("ID do carro a remover: ");
                    int idRem = sc.nextInt();
                    sc.nextLine();

                    Optional<Carro> optCarroRem = carroController.buscarPorId(idRem);
                    if (optCarroRem.isEmpty()) {
                        System.out.println("Carro não encontrado com ID: " + idRem);
                    } else {
                        carroController.remover(idRem);
                        System.out.println("Carro removido com sucesso!");
                    }
                }
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }


    private static void menuClientes() {
        int opcao;
        do {
            System.out.println("\n--- Menu Clientes ---");
            System.out.println("1. Listar clientes");
            System.out.println("2. Adicionar cliente");
            System.out.println("3. Aplicar multa");
            System.out.println("4. Remover multa");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();
        
            switch (opcao) {
                case 1 -> {
                    List<Cliente> clientes = clienteController.listarClientes();
                    if (clientes.isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado.");
                    } else {
                        for (Cliente c : clientes) {
                            System.out.println(c);
                        }
                    }
                }
                case 2 -> {
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Data de nascimento (AAAA-MM-DD): ");
                    LocalDate nascimento = LocalDate.parse(sc.nextLine());
                
                    Cliente novo = clienteController.adicionarCliente(nome, cpf, telefone, email, nascimento);
                    System.out.println("Cliente adicionado com ID: " + novo.getId());
                }
                case 3 -> {
                    System.out.print("ID do cliente para aplicar multa: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    if (clienteController.aplicarMulta(id)) {
                        System.out.println("Multa aplicada com sucesso.");
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                }
                case 4 -> {
                    System.out.print("ID do cliente para remover multa: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    if (clienteController.removerMulta(id)) {
                        System.out.println("Multa removida com sucesso.");
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                }
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }


    private static void menuFuncionarios() {
        int opcao;
        do {
            System.out.println("\n--- Menu Funcionários ---");
            System.out.println("1. Listar funcionários");
            System.out.println("2. Adicionar funcionário");
            System.out.println("3. Atualizar turno");
            System.out.println("4. Atualizar salário");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();
        
            switch (opcao) {
                case 1 -> {
                    System.out.println("\n--- Lista de Funcionários ---");
                    funcionarioController.listarFuncionarios().forEach(System.out::println);
                }
                case 2 -> {
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Data de nascimento (yyyy-mm-dd): ");
                    String dataNascStr = sc.nextLine();
                    LocalDate dataNascimento = LocalDate.parse(dataNascStr);
                    System.out.print("Turno (MANHA, TARDE, NOITE): ");
                    Turno turno = Turno.valueOf(sc.nextLine().toUpperCase());
                    System.out.print("Cargo: ");
                    String cargo = sc.nextLine();
                    System.out.print("Salário: ");
                    double salario = sc.nextDouble();
                    System.out.print("Comissão: (0%-20%)");
                    double comissao = sc.nextDouble();
                    sc.nextLine();
                
                    funcionarioController.adicionarFuncionario(nome, cpf, telefone, email, dataNascimento, turno, cargo, salario, comissao);
                    System.out.println("Funcionário adicionado com sucesso!");
                }
                case 3 -> {
                    System.out.print("ID do funcionário para atualizar turno: ");
                    int idTurno = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Novo turno (MANHA, TARDE, NOITE): ");
                    Turno novoTurno = Turno.valueOf(sc.nextLine().toUpperCase());
                
                    if (funcionarioController.atualizarTurno(idTurno, novoTurno)) {
                        System.out.println("Turno atualizado com sucesso!");
                    } else {
                        System.out.println("Funcionário não encontrado.");
                    }
                }
                case 4 -> {
                    System.out.print("ID do funcionário para atualizar salário: ");
                    int idSalario = sc.nextInt();
                    System.out.print("Novo salário: ");
                    double novoSalario = sc.nextDouble();
                    sc.nextLine();
                
                    if (funcionarioController.atualizarSalario(idSalario, novoSalario)) {
                        System.out.println("Salário atualizado com sucesso!");
                    } else {
                        System.out.println("Funcionário não encontrado.");
                    }
                }
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void menuAluguel() {
    int opcao;
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
        opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1 -> {
                List<Aluguel> ativos = aluguelController.listarAtivos();
                if (ativos.isEmpty()) {
                    System.out.println("Nenhum aluguel ativo.");
                } else {
                    System.out.println("--- Aluguéis Ativos ---");
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
                System.out.print("ID do cliente (deve estar cadastrado): ");
                List<Cliente> clientes = clienteController.listarClientes();
                    if (clientes.isEmpty()) {
                        System.out.println("\nNenhum cliente cadastrado.");
                    } else {
                        for (Cliente c : clientes) {
                            System.out.println("\n" + c);
                        }
                    }
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
                    motoController.listar();
                    System.out.print("ID da moto para alugar: ");
                    int motoId = sc.nextInt();
                    sc.nextLine();
                    Optional<Moto> motoOpt = motoController.buscarPorId(motoId);
                    if (motoOpt.isEmpty()) {
                        System.out.println("Moto não encontrada.");
                        break;
                    }
                    veiculo = motoOpt.get();
                } else if (tipoVeiculo.equals("carro")) {
                    carroController.listar();
                    System.out.print("ID do carro para alugar: ");
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

                System.out.print("ID do funcionário responsável: ");
                funcionarioController.listarFuncionarios().forEach(System.out::println);
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
                    System.out.println("Aluguel cadastrado com sucesso!");
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

                long dias = ChronoUnit.DAYS.between(aluguel.getDataInicio(), aluguel.getDataFim());
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
                        aluguelController.exibirSalarioEComissao(f);
                    }
                }
            }
            case 0 -> System.out.println("Voltando ao menu principal...");
            default -> System.out.println("Opção inválida.");
        }
    } while (opcao != 0);
}

}
