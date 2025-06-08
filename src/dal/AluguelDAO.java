package dal;

import model.Aluguel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AluguelDAO {
    private static final String CAMINHO = "src/dados";

    public static void salvar(List<Aluguel> alugueis) throws IOException {
        File diretorio = new File(CAMINHO);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO + "/alugueis.ser"))) {
            oos.writeObject(alugueis);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Aluguel> carregar() throws IOException, ClassNotFoundException {
        File arquivo = new File(CAMINHO + "/alugueis.ser");
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Aluguel>) ois.readObject();
        }
    }
}
