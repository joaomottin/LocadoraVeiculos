package factory;

import model.Carenagem;
import model.Moto;
import util.Validacao;

public class MotoFactory {

    public static Moto criarMoto(String marca, String modelo, int anoFabricacao, String placa, double precoDiaria, boolean disponivel, int cilindradas, Carenagem tipoCarenagem) {

        Validacao.validarStringVazia(marca, "Marca");
        Validacao.validarStringVazia(modelo, "Modelo");
        Validacao.validarNumeroEntre(anoFabricacao, 1800, 2025, "Ano de fabricação");
        Validacao.validarPlacaVeicular(placa, "Placa");
        Validacao.validarNumeroPositivo(precoDiaria, "Preço da diária");
        Validacao.validarNumeroEntre(cilindradas, 1, 2500, "Cilindradas");
        
        return new Moto(marca, modelo, anoFabricacao, placa, precoDiaria, disponivel, cilindradas, tipoCarenagem);
    }
}
