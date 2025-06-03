package util;

public class GeradorID {
    private static int nextId = 1;

    public static int getNextId() {
        return nextId++;
    }

    public static void setNextId(int id) {
        if (id >= nextId) {
            nextId = id + 1;
        }
    }
}
