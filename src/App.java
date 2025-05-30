public class App {
    public static void main(String[] args) {
        System.out.println("Locadora De Veículos");
        try {
            LocadoraView.main(args);
        } catch (Exception e) {
            System.err.println("Erro inesperado na aplicação: " + e.getMessage());
        }
    }
}
