package view;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import controller.ClienteController;
import model.Cliente;

public class ClienteView {
    private final ClienteController clienteController;
    private final Scanner sc;

    public ClienteView(ClienteController clienteController, Scanner sc) {
        this.clienteController = clienteController;
        this.sc = sc;
    }     

    public void menu() {
            int opcao = -1;
            do {
                System.out.println("\n--- Menu Clientes ---");
                System.out.println("1. Listar clientes");
                System.out.println("2. Adicionar cliente");
                System.out.println("3. Aplicar multa");
                System.out.println("4. Remover multa");
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
                        System.out.println("\n--- Lista de Clientes ---");
                        List<Cliente> clientes = clienteController.listarClientes();
                        if (clientes.isEmpty()) {
                            System.out.println("Nenhum cliente.");
                        } else {
                            clientes.forEach(System.out::println);
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
                    
                        try {
                            clienteController.cadastrar(nome, cpf, telefone, email, nascimento);
                            System.out.println("Cliente adicionado com sucesso!");
                        } catch (Exception e) {
                            System.out.println("Erro ao adicionar cliente: " + e.getMessage());
                        }
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

}
