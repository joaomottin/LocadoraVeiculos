package view;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import controller.FuncionarioController;
import model.Funcionario;
import model.Turno;

public class FuncionarioView {
        private final FuncionarioController funcionarioController;
    private final Scanner sc;

    public FuncionarioView(FuncionarioController funcionarioController, Scanner sc) {
        this.funcionarioController = funcionarioController;
        this.sc = sc;
    }  

    public void menu() {
            int opcao = -1;
            do {
                System.out.println("\n--- Menu Funcionários ---");
                System.out.println("1. Listar funcionários");
                System.out.println("2. Adicionar funcionário");
                System.out.println("3. Atualizar turno");
                System.out.println("4. Atualizar salário");
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
                        System.out.println("\n--- Lista de Funcionários ---");
                        List<Funcionario> lista = funcionarioController.listarFuncionarios();
                        if (lista.isEmpty()) {
                            System.out.println("Nenhum funcionário cadastrado.");
                        } else {
                            lista.forEach(System.out::println);
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
                        System.out.print("Data de nascimento (yyyy-mm-dd): ");
                        String dataNascStr = sc.nextLine();
                        LocalDate dataNascimento = LocalDate.parse(dataNascStr);
                        Turno turno = null;
                            while (turno == null) {
                                System.out.print("Turno (Manha, Tarde, Noite): ");
                                String inputTurno = sc.nextLine().toUpperCase();
                                try {
                                    turno = Turno.valueOf(inputTurno);
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Turno inválido! Tente novamente.");
                                }
                            }
                        System.out.print("Cargo: ");
                        String cargo = sc.nextLine();
                        System.out.print("Salário: ");
                        double salario = sc.nextDouble();
                        System.out.print("Comissão: (0%-20%)");
                        double comissao = sc.nextDouble();
                        sc.nextLine();
                    
                        try {
                            funcionarioController.cadastrar(nome, cpf, telefone, email, dataNascimento, turno, cargo, salario, comissao);
                            System.out.println("Funcionário adicionado com sucesso!");
                        } catch (Exception e) {
                            System.out.println("Erro ao adicionar funcionário: " + e.getMessage());
                        }
                    }
                    case 3 -> {
                        System.out.print("ID do funcionário para atualizar turno: ");
                        int idTurno = sc.nextInt();
                        sc.nextLine();
                        Turno novoTurno = null;
                            while (novoTurno == null) {
                                System.out.print("Novo turno (Manha, Tarde, Noite): ");
                                String inputTurno = sc.nextLine().toUpperCase();
                                try {
                                    novoTurno = Turno.valueOf(inputTurno);
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Turno inválido! Tente novamente.");
                                }
                            }

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

                        if (novoSalario <= 1500) {
                            System.out.println("Salário inválido. Sem escravidão por aqui...");
                        } else {
                            if (funcionarioController.atualizarSalario(idSalario, novoSalario)) {
                                System.out.println("Salário atualizado com sucesso!");
                            } else {
                                System.out.println("Funcionário não encontrado.");
                            }
                        }
                    }

                    case 0 -> System.out.println("Voltando ao menu principal...");
                    default -> System.out.println("Opção inválida.");
                }
            } while (opcao != 0);
        }
}
