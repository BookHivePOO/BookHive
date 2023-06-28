package model;

public class Genero {
    private final int idGenero;
    private final String genero;

    public Genero(int idGenero, String genero) {
        this.idGenero = idGenero;
        this.genero = genero;
    }

    /**
     * Retorna o ID do gênero.
     *
     * @return ID do gênero
     */
    public int getIdGenero() {
        return idGenero;
    }

    /**
     * Retorna a descrição do gênero.
     *
     * @return descrição do gênero
     */
    public String getDescricao() {
        return genero;
    }
}
