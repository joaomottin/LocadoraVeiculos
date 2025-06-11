package model;

public enum Carenagem {
    ESPORTIVA("Esportiva"),
    CUSTOM("Custom"),
    NAKED("Naked"),
    TRAIL("Trail"),
    SCOOTER("Scooter");

    private final String descricao;

    Carenagem(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
