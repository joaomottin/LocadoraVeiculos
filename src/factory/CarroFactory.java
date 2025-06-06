package factory;

import exception.StringVaziaException;
import model.Carro;

public class CarroFactory {

    public static Carro criarCarro(String marca, String modelo, int anoFabricacao, String placa, double precoDiaria, boolean disponivel, int numeroPortas, String tipoCombustivel ) throws Exception {
        
        if (marca == null || marca.isEmpty()) {
            throw new StringVaziaException("Marca não pode ser vazia");
        }
        
        if (modelo == null || modelo.isEmpty()) {
            throw new StringVaziaException("Modelo não pode ser vazio");
        }
        
        if (anoFabricacao < 1800 || anoFabricacao > 2025) {
            throw new IllegalArgumentException("Ano de fabricação inválido");
        }
        
        if (placa == null || placa.isEmpty()) {
            throw new StringVaziaException("Placa não pode ser vazia");
        }
        
        if (precoDiaria < 0) {
            throw new IllegalArgumentException("Preço da diária deve ser positivo");
        }
        
        if (numeroPortas <= 0 || numeroPortas > 4)  {
            throw new IllegalArgumentException("Número de portas deve ser entre 2 ou 4");
        }
        
        if (tipoCombustivel == null || tipoCombustivel.isEmpty()) {
            throw new StringVaziaException("Tipo de combustível não pode ser vazio");
        }

        return new Carro(marca, modelo, anoFabricacao, placa, precoDiaria, disponivel, numeroPortas, tipoCombustivel);
    }
}
