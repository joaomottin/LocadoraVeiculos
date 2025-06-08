package view;

import controller.MotoController;
import model.Moto;

import java.util.Optional;
import java.util.Scanner;

public class MotoView {
    private final MotoController motoController;
    private final Scanner sc;

    public MotoView(MotoController motoController, Scanner sc) {
        this.motoController = motoController;
        this.sc = sc;
    }        
    
    public void menu() {
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
                        try {
                            motoController.listar().forEach(System.out::println);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
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
                        System.out.print("Cilindradas: ");
                        int cilindradas = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Tipo de carenagem: ");
                        String tipoCarenagem = sc.nextLine();
                    
                        try {
                            motoController.cadastrar(marca, modelo, ano, placa, preco, true, cilindradas, tipoCarenagem);
                            System.out.println("Moto adicionada com sucesso!");
                        } catch (Exception e) {
                            System.out.println("Erro(s) ao adicionar moto: " + e.getMessage());
                        }

                    }
                    case 3 -> { 
                        System.out.print("ID da moto a modificar: ");
                        motoController.listar().forEach(System.out::println);
                        int idMod = sc.nextInt();
                        sc.nextLine();
                                        
                        Optional<Moto> optMoto = motoController.buscarPorId(idMod);
                        if (optMoto.isEmpty()) {
                            System.out.println("Moto não encontrada com ID: " + idMod);
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
                                System.out.print("Novas cilindradas: ");
                                int cilindradasAtualizado = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Novo tipo de carenagem: ");
                                String carenagemAtualizada = sc.nextLine();
                            
                                motoController.atualizar(idMod, marcaAtualizada, modeloAtualizado, anoAtualizado, placaAtualizada, precoAtualizado, cilindradasAtualizado, carenagemAtualizada);
                            
                                System.out.println("Moto atualizada com sucesso!");
                            } catch (Exception e) {
                                System.out.println("Erro ao atualizar moto: " + e.getMessage());
                            }
                        }
                    }

                    case 4 -> {
                        System.out.print("ID da moto a remover: ");
                        motoController.listar().forEach(System.out::println);
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
    }