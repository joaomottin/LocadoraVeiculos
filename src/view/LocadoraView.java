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
                    System.out.print("Disponível (true/false): ");
                    boolean disponivel = sc.nextBoolean();
                    sc.nextLine();
                    System.out.print("Cilindradas: ");
                    int cilindradas = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Tipo de carenagem: ");
                    String carenagem = sc.nextLine();

                    Moto novaMoto = new Moto(0, marca, modelo, ano, placa, preco, disponivel, cilindradas, carenagem);
                    motoController.cadastrar(novaMoto);
                    System.out.println("Moto adicionada com sucesso!");
                }
                case 3 -> {
                }
                case 4 -> {
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
                    System.out.print("Disponível (true/false): ");
                    boolean disponivel = sc.nextBoolean();
                    sc.nextLine();
                    System.out.print("Número de portas: ");
                    int portas = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Tipo de combustível: ");
                    String combustivel = sc.nextLine();

                    Carro novoCarro = new Carro(0, marca, modelo, ano, placa, preco, disponivel, portas, combustivel);
                    carroController.cadastrar(novoCarro);
                    System.out.println("Carro adicionado com sucesso!");

                }
                case 3 -> {
                }
                case 4 -> {
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
