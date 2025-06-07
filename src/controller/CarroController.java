    package controller;

    import dal.CarroDAO;
    import factory.CarroFactory;
    import model.Carro;
    import util.GeradorID;
    import util.Log;

    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;

    public class CarroController implements Gerenciavel{
        private List<Carro> carros;

        public CarroController() {
            try {
                carros = CarroDAO.carregar();
                Log.carregar("Carros carregados: " + carros.size());
                int maiorId = carros.stream()
                                .mapToInt(Carro::getId)
                                .max()
                                .orElse(0);
                GeradorID.setNextIdVeiculo(maiorId);
            } catch (IOException | ClassNotFoundException e) {
                carros = new ArrayList<>();
                throw new IllegalArgumentException("Arquivo carros.ser não encontrado ou corrompido.");
            }
        }

        public static List<Carro> carregar() throws IOException, ClassNotFoundException {
            return CarroDAO.carregar();
        }

        private void salvar() {
            try {
                CarroDAO.salvar(carros);
            } catch (IOException e) {
                throw new IllegalArgumentException("Erro ao salvar carros: " + e.getMessage());
            }
        }

        public Optional<Carro> buscarPorId(int id) {
            return carros.stream()
                        .filter(c -> c.getId() == id)
                        .findFirst();
        }

        public void cadastrar(String marca, String modelo, int anoFabricacao, String placa, double precoDiaria, boolean disponivel, int numeroPortas, String tipoCombustivel) throws Exception {
            Carro carro = CarroFactory.criarCarro(marca, modelo, anoFabricacao, placa, precoDiaria, disponivel, numeroPortas, tipoCombustivel);
            cadastrar(carro);
            Log.cadastrar("Carro cadastrado: " + carro.getMarca() + " " + carro.getModelo() + " (" + carro.getPlaca() + ")");
        }

        public void cadastrar(Carro carro) {
            carros.add(carro);
            salvar();
        }
        

        @Override
        public List<String> listar() {
            if (carros.isEmpty()) {
                throw new IllegalArgumentException("Nenhum carro cadastrado.");
            }
            return carros.stream().map(Carro::toString).toList();
        }

        public void atualizar(int id, String marca, String modelo, int anoFabricacao, String placa, double precoDiaria, int numeroPortas, String tipoCombustivel) throws Exception {

            Optional<Carro> optCarro = buscarPorId(id);
            if (optCarro.isEmpty()) {
                throw new Exception("Carro não encontrado com ID: " + id);
            }

            CarroFactory.criarCarro(marca, modelo, anoFabricacao, placa, precoDiaria, true, numeroPortas, tipoCombustivel);

            Carro carro = optCarro.get();
            carro.setMarca(marca);
            carro.setModelo(modelo);
            carro.setAnoFabricacao(anoFabricacao);
            carro.setPlaca(placa);
            carro.setPrecoDiaria(precoDiaria);
            carro.setNumeroPortas(numeroPortas);
            carro.setTipoCombustivel(tipoCombustivel);

            Log.alterar("Carro alterado: " + carro.getMarca() + " " + carro.getModelo() + " (" + carro.getPlaca() + ")");
            salvar();
        }

        @Override
        public void remover(int id) {
            Optional<Carro> carroOptional = buscarPorId(id);
            if (carroOptional.isPresent()) {
                Carro carro = carroOptional.get();
                carros.remove(carro);
                salvar();
                Log.excluir("Carro removida | ID = " + carro.getId());
            }
        }
    }
