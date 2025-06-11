package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.AluguelController;
import controller.CarroController;
import controller.ClienteController;
import controller.FuncionarioController;
import controller.MotoController;

public class LocadoraView {
    private static Scanner sc = new Scanner(System.in);
    private static MotoController motoController = new MotoController();
    private static CarroController carroController = new CarroController();
    private static ClienteController clienteController = new ClienteController();
    private static FuncionarioController funcionarioController = new FuncionarioController();
    private static AluguelController aluguelController = new AluguelController();

    private static MotoView motoView = new MotoView(motoController, sc);
    private static CarroView carroView = new CarroView(carroController, sc);
    private static ClienteView clienteView = new ClienteView(clienteController, sc);
    private static FuncionarioView funcionarioView = new FuncionarioView(funcionarioController, sc);
    private static AluguelView aluguelView = new AluguelView(aluguelController,clienteController,motoController,carroController,funcionarioController, sc);

    public static void main(String[] args) {
        int opcao = -1;
        do {
            System.out.println("\n====== LOCADORA DE VEÍCULOS ======");
            System.out.println("1. Gerenciar Motos");
            System.out.println("2. Gerenciar Carros");
            System.out.println("3. Gerenciar Clientes");
            System.out.println("4. Gerenciar Funcionários");
            System.out.println("5. Gerenciar Aluguéis");
            System.out.println("0. Sair");
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
                case 1 -> motoView.menu();
                case 2 -> carroView.menu();
                case 3 -> clienteView.menu();
                case 4 -> funcionarioView.menu();
                case 5 -> aluguelView.menu();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}
