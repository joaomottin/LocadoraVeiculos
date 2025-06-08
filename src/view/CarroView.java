package view;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import controller.CarroController;
import model.Carro;

public class CarroView {
    private final CarroController carroController;
    private final Scanner sc;

    public CarroView(CarroController carroController, Scanner sc) {
        this.carroController = carroController;
        this.sc = sc;
    }
    
    public void menu() {
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
                        List<String> carros = carroController.listar();
                        if (carros.isEmpty()) {
                            System.out.println("Nenhum carro.");
                        } else {
                            carros.forEach(System.out::println);
                        }
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
                    
                        try {
                            carroController.cadastrar(marca, modelo, ano, placa, preco, true, portas, combustivel);
                            System.out.println("Carro adicionado com sucesso!");
                        } catch (Exception e) {
                            System.out.println("Erro(s) ao adicionar carro: " + e.getMessage());
                        }

                    }
                    case 3 -> {
                        System.out.print("ID do carro a modificar: ");
                        carroController.listar().forEach(System.out::println);
                        int idMod = sc.nextInt();
                        sc.nextLine();

                        Optional<Carro> optCarro = carroController.buscarPorId(idMod);
                        if (optCarro.isEmpty()) {
                            System.out.println("Carro não encontrado com ID: " + idMod);
                        } else {
                            try {
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
                            
                                carroController.atualizar(idMod, marcaAtualizada, modeloAtualizado, anoAtualizado, placaAtualizada, precoAtualizado, portasAtualizado, combustivelAtualizado);
                            
                                System.out.println("Carro atualizado com sucesso!");
                            } catch (Exception e) {
                                System.out.println("Erro ao atualizar carro: " + e.getMessage());
                            }
                        }
                    }
                    case 4 -> {
                        System.out.print("ID do carro a remover: ");
                        carroController.listar().forEach(System.out::println);
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
}
