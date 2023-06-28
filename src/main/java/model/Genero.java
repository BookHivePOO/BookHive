package model;

public class Genero {
    private final int idGenero;
    private final String genero;

    public Genero(int idGenero, String genero) {
        this.idGenero = idGenero;
        this.genero = genero;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public String getDescricao() {
        return genero;
    }
}
