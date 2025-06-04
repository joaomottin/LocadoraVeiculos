package dal;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

public abstract class ClienteDAO {
    private static final String CAMINHO = "src/dados/cliente";

    public static void salvar(List<Cliente> clientes) throws IOException {
        File diretorio = new File(CAMINHO);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(diretorio + "/clientes.ser"))) {
            oos.writeObject(clientes);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Cliente> carregar() throws IOException, ClassNotFoundException {
        File arquivo = new File(CAMINHO + "/clientes.ser");
        if (!arquivo.exists()) {
            return new ArrayList<Cliente>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Cliente>) ois.readObject();
        }
    }
}
