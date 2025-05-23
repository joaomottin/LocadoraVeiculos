package view;

import controller.MotoController;
import controller.CarroController;

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

        ClienteController cc = new ClienteController();
        Cliente c1 = cc.adicionarCliente("João", "123.456.789-00", "11999999999", "joao@email.com", LocalDate.of(1990, 1, 15));

        cc.listarClientes().forEach(System.out::println);

        cc.aplicarMulta(c1.getId());
        cc.removerMulta(c1.getId());
        
        FuncionarioController fc = new FuncionarioController();

        Turno t1 = new Turno(1, "Manhã", "08:00", "12:00");
        
        Funcionario f1 = fc.adicionarFuncionario("Ana", "123.456.789-00", "11999999999", "ana@email.com",

        LocalDate.of(1990, 5, 10), t1, "Atendente", 2500.0, 0.05);

        fc.calcularComissao(f1.getId(), 10000).ifPresent(comissao -> System.out.println("Comissão: " + comissao));

    }
}
