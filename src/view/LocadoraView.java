package view;

import controller.MotoController;

public class LocadoraView {
    public static void main(String[] args) {
        MotoController mc = new MotoController();

        mc.adicionarMoto("Yamaha", "R15", 2020, "ABC-1234", 150.0, true, 150, "Carenada");
        mc.adicionarMoto("Honda", "CG 160", 2018, "XYZ-5678", 80.0, true, 160, "Naked");

        mc.listarMotos().forEach(System.out::println);

        mc.alugarMoto(1);
        mc.devolverMoto(1);
    }
}
