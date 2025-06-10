package model;

public enum Turno {
    MANHA("06:00 às 14:00"),
    TARDE("14:00 às 22:00"),
    NOITE("22:00 às 06:00");

    private final String horario;

    Turno(String horario) {
        this.horario = horario;
    }
    public String getHorario() {
        return horario;
    }

    @Override
    public String toString() {
        return name() + " (" + horario + ")";
    }
}