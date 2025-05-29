import controller.AluguelController;
import controller.CarroController;
import controller.ClienteController;
import controller.MotoController;
import exception.VeiculoIndisponivelException;
import model.*;

import java.time.LocalDate;
import java.util.List;

public class LocadoraView {
    public static void main(String[] args) {
        CarroController carroController = new CarroController();
        MotoController motoController = new MotoController();
        ClienteController clienteController = new ClienteController();
        AluguelController aluguelController = new AluguelController();

        
        carroController.cadastrarCarro("ABC1234", "Toyota", "Corolla", 2020, 100.0, 4);
        motoController.cadastrarMoto("XYZ5678", "Honda", "CG 160", 2021, 50.0, 160);

        
        clienteController.cadastrarCliente("João Silva", "123.456.789-00");

        
        List<Veiculo> veiculos = carroController.listarVeiculos();
        veiculos.addAll(motoController.listarVeiculos());
        System.out.println("Veículos disponíveis:");
        for (Veiculo v : veiculos) {
            System.out.println(v);
        }

        
        Cliente c1 = clienteController.buscarClientePorCpf("123.456.789-00");
        Veiculo veiculoParaAlugar = veiculos.get(0); // Aluga o primeiro veículo da lista

        try {
            boolean sucesso = aluguelController.alugarVeiculo(c1, veiculoParaAlugar,
                    LocalDate.of(2025, 5, 20), LocalDate.of(2025, 5, 24));

            if (sucesso) {
                System.out.println("Aluguel registrado com sucesso!");
            } else {
                System.out.println("Não foi possível alugar o veículo.");
            }
        } catch (VeiculoIndisponivelException e) {
            System.out.println("Não foi possível alugar: " + e.getMessage());
        }

        
        System.out.println("Aluguéis registrados:");
        for (Aluguel aluguel : aluguelController.listarAlugueis()) {
            System.out.println(aluguel);
        }
    }
}
