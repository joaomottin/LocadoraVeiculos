package dal;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Funcionario;

public abstract class FuncionarioDAO {
    private static final String CAMINHO = "src/dados";

    public static void salvar(List<Funcionario> funcionarios) throws IOException {
        File diretorio = new File(CAMINHO);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(diretorio + "/funcionarios.ser"))) {
            oos.writeObject(funcionarios);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Funcionario> carregar() throws IOException, ClassNotFoundException {
        File arquivo = new File(CAMINHO + "/funcionarios.ser");
        if (!arquivo.exists()) {
            return new ArrayList<Funcionario>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Funcionario>) ois.readObject();
        }
    }
}
