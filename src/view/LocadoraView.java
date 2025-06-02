package view;

import controller.MotoController;
import controller.CarroController;
import controller.ClienteController;
import controller.FuncionarioController;
import controller.AluguelController;
import model.Cliente;
import model.Funcionario;
import model.Turno;
import model.Veiculo;

import java.time.LocalDate;

public class LocadoraView {
    public static void main(String[] args) {
        MotoController mc = new MotoController();

        mc.adicionarMoto("Yamaha", "R15", 2020, "ABC-1234", 150.0, true, 150, "Carenada");
        mc.adicionarMoto("Honda", "CG 160", 2018, "XYZ-5678", 80.0, true, 160, "Naked");

        mc.listarMotos().forEach(System.out::println);

        mc.alugarMoto(1);
        mc.devolverMoto(1);

        CarroController cc = new CarroController();
        cc.adicionarCarro("Toyota", "Corolla", 2020, "ABC-1234", 150.0, true, 4, "Gasolina");

        cc.listarCarros().forEach(System.out::println);

        cc.alugarCarro(1);
        cc.devolverCarro(1);

        ClienteController clienteController = new ClienteController();
        Cliente c1 = clienteController.adicionarCliente("João", "123.456.789-00", "11999999999", "joao@email.com", LocalDate.of(1990, 1, 15));

        clienteController.listarClientes().forEach(System.out::println);

        clienteController.aplicarMulta(c1.getId());
        clienteController.removerMulta(c1.getId());

        FuncionarioController fc = new FuncionarioController();
        Turno t1 = Turno.MANHA;

        Funcionario f1 = fc.adicionarFuncionario("Ana", "123.456.789-00", "11999999999", "ana@email.com",
            LocalDate.of(1990, 5, 10), t1, "Atendente", 2500.0, 0.05);

        fc.calcularComissao(f1.getId(), 10000).ifPresent(comissao -> System.out.println("Comissão: " + comissao));

        AluguelController aluguelController = new AluguelController();

        Veiculo veiculoParaAlugar = cc.buscarPorId(1).orElse(null);

        if (veiculoParaAlugar != null) {
            boolean sucesso = aluguelController.alugarVeiculo(c1, veiculoParaAlugar,
                LocalDate.of(2025, 5, 20), LocalDate.of(2025, 5, 24));

            if (sucesso) {
                System.out.println("Aluguel registrado com sucesso!");
            } else {
                System.out.println("Não foi possível alugar o veículo.");
            }

            aluguelController.listarTodos().forEach(System.out::println);

            aluguelController.devolverVeiculo(veiculoParaAlugar);
        }
    }
}
