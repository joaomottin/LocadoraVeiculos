package view;



import java.util.Scanner;

import controller.AluguelController;
import controller.CarroController;
import controller.ClienteController;
import controller.FuncionarioController;
import controller.MotoController;

public class LocadoraView {
    private static final Scanner sc = new Scanner(System.in);
    private static final MotoController motoController = new MotoController();
    private static final CarroController carroController = new CarroController();
    private static final ClienteController clienteController = new ClienteController();
    private static final FuncionarioController funcionarioController = new FuncionarioController();
    private static final AluguelController aluguelController = new AluguelController();

    private static final MotoView motoView = new MotoView(motoController, sc);
    private static final CarroView carroView = new CarroView(carroController, sc);
    private static final ClienteView clienteView = new ClienteView(clienteController, sc);
    private static final FuncionarioView funcionarioView = new FuncionarioView(funcionarioController, sc);
    private static final AluguelView aluguelView = new AluguelView(aluguelController,clienteController,motoController,carroController,funcionarioController, sc);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n====== LOCADORA DE VEÍCULOS ======");
            System.out.println("1. Gerenciar Motos");
            System.out.println("2. Gerenciar Carros");
            System.out.println("3. Gerenciar Clientes");
            System.out.println("4. Gerenciar Funcionários");
            System.out.println("5. Gerenciar Aluguéis");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

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
