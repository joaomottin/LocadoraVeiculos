package factory;

import model.Carro;
import model.Moto;
import model.Veiculo;

public class VeiculoFactory {
    public static Veiculo criarVeiculo(String tipo, int id, String modelo, String placa, double precoDiaria, Object atributoEspecifico) {
        switch (tipo.toLowerCase()) {
            case "carro":
                int portas = (int) atributoEspecifico;
                return new Carro(id, modelo, placa, precoDiaria, portas);
            case "moto":
                boolean bau = (boolean) atributoEspecifico;
                return new Moto(id, modelo, placa, precoDiaria, bau);
            default:
                throw new IllegalArgumentException("Tipo de veículo desconhecido: " + tipo);
        }
    }
}
