package view;

import controller.*;
import model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
            System.out.println("3. Alugar moto");
            System.out.println("4. Devolver moto");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.println("\n--- Lista de Motos ---");
                    List<Moto> listaMoto = new ArrayList<>();
                    try {
                        listaMoto = MotoController.carregar();
                    }catch (IOException e) {
                        System.err.println("Arquivo não encontrado " + e.getMessage());
                    }catch (ClassNotFoundException e){
                        System.err.println("Arquivo corrompido");
                    }
                    motoController.listarMotos().forEach(System.out::println);
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
                    System.out.print("Disponível (true/false): ");
                    boolean disponivel = sc.nextBoolean();
                    sc.nextLine();
                    System.out.print("Cilindradas: ");
                    int cilindradas = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Tipo de carenagem: ");
                    String carenagem = sc.nextLine();

                    motoController.adicionarMoto(marca, modelo, ano, placa, preco, disponivel, cilindradas, carenagem);
                    System.out.println("Moto adicionada com sucesso!");
                }
                case 3 -> {
                    System.out.print("ID da moto para alugar: ");
                    int idAlugar = sc.nextInt();
                    sc.nextLine();
                    if (motoController.alugarMoto(idAlugar)) {
                        System.out.println("Moto alugada com sucesso!");
                    } else {
                        System.out.println("Falha ao alugar moto. Verifique se a moto está disponível.");
                    }
                }
                case 4 -> {
                    System.out.print("ID da moto para devolver: ");
                    int idDevolver = sc.nextInt();
                    sc.nextLine();
                    if (motoController.devolverMoto(idDevolver)) {
                        System.out.println("Moto devolvida com sucesso!");
                    } else {
                        System.out.println("Falha ao devolver moto. Verifique o ID e o status da moto.");
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
            System.out.println("3. Alugar carro");
            System.out.println("4. Devolver carro");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.println("\n--- Lista de Carros ---");
                    List<Carro> listaCarro = new ArrayList<>();
                    try {
                        listaCarro = CarroController.carregar();
                    } catch (IOException e) {
                        System.err.println("Arquivo não encontrado: " + e.getMessage());
                    } catch (ClassNotFoundException e) {
                        System.err.println("Arquivo corrompido");
                    }
                    carroController.listarCarros().forEach(System.out::println);
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
                    System.out.print("Disponível (true/false): ");
                    boolean disponivel = sc.nextBoolean();
                    sc.nextLine();
                    System.out.print("Número de portas: ");
                    int portas = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Tipo de combustível: ");
                    String combustivel = sc.nextLine();

                    carroController.adicionarCarro(marca, modelo, ano, placa, preco, disponivel, portas, combustivel);
                    System.out.println("Carro adicionado com sucesso!");
                }
                case 3 -> {
                    System.out.print("ID do carro para alugar: ");
                    int idAlugar = sc.nextInt();
                    sc.nextLine();
                    if (carroController.alugarCarro(idAlugar)) {
                        System.out.println("Carro alugado com sucesso!");
                    } else {
                        System.out.println("Falha ao alugar carro. Verifique se o carro está disponível.");
                    }
                }
                case 4 -> {
                    System.out.print("ID do carro para devolver: ");
                    int idDevolver = sc.nextInt();
                    sc.nextLine();
                    if (carroController.devolverCarro(idDevolver)) {
                        System.out.println("Carro devolvido com sucesso!");
                    } else {
                        System.out.println("Falha ao devolver carro. Verifique o ID e o status do carro.");
                    }
                }
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }


    private static void menuClientes() {
        System.out.println("\n--- Clientes ---");
        clienteController.listarClientes().forEach(System.out::println);
        // Ex: adicionar cliente, aplicar multa, etc.
    }

    private static void menuFuncionarios() {
        System.out.println("\n--- Funcionários ---");
        funcionarioController.listarFuncionarios().forEach(System.out::println);
        // Ex: cadastrar, calcular comissão, etc.
    }

    private static void menuAluguel() {
        System.out.println("\n--- Aluguel de Veículos ---");
        aluguelController.listarTodos().forEach(System.out::println);
        // Ex: alugar, devolver, ver histórico
    }
}
