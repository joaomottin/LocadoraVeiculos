package factory;

import model.Carro;
import util.Validacao;

public class CarroFactory {

    public static Carro criarCarro(String marca, String modelo, int anoFabricacao, String placa,
                                   double precoDiaria, boolean disponivel,
                                   int numeroPortas, String tipoCombustivel) {

        Validacao.validarStringVazia(marca, "Marca");
        Validacao.validarStringVazia(modelo, "Modelo");
        Validacao.validarNumeroEntre(anoFabricacao, 1800, 2025, "Ano de fabricação");
        Validacao.validarStringVazia(placa, "Placa");
        Validacao.validarNumeroPositivo(precoDiaria, "Preço da diária");
        Validacao.validarNumeroEntre(numeroPortas, 2, 4, "Número de portas");
        Validacao.validarStringVazia(tipoCombustivel, "Tipo de combustível");

        return new Carro(marca, modelo, anoFabricacao, placa, precoDiaria, disponivel, numeroPortas, tipoCombustivel);
    }
}
