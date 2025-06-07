package factory;

import exception.StringVaziaException;
import model.Moto;

public class MotoFactory {

    public static Moto criarMoto(String marca, String modelo, int anoFabricacao, String placa, double precoDiaria, boolean disponivel, int cilindradas, String tipoCarenagem) throws Exception {

        //checar aula -. aula 20 pt2
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
        
        if (cilindradas <= 0 || cilindradas > 2500) {
            throw new IllegalArgumentException("Cilindradas deve ser entre 1 e 2500");
        }
        
        if (tipoCarenagem == null || tipoCarenagem.isEmpty()) {
            throw new StringVaziaException("Tipo de carenagem não pode ser vazio");
        }

        return new Moto(marca, modelo, anoFabricacao, placa, precoDiaria, disponivel, cilindradas, tipoCarenagem);
    }
}
