package util;

public class GeradorID {
    private static int nextIdPessoa = 1;
    private static int nextIdVeiculo = 1;

    public static int getNextIdPessoa() {
        return nextIdPessoa++;
    }

    public static void setNextIdPessoa(int id) {
        if (id >= nextIdPessoa) {
            nextIdPessoa = id + 1;
        }
    }
    
    public static int getNextIdVeiculo() {
        return nextIdVeiculo++;
    }

    public static void setNextIdVeiculo(int id) {
        if (id >= nextIdVeiculo) {
            nextIdVeiculo = id + 1;
        }
    }
}
