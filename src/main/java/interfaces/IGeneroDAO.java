package interfaces;

import model.Genero;

import java.util.List;

public interface IGeneroDAO {
    int obterIdGenero(String genero);
    void cadastrarGenero(String genero);
    List<Genero> listarGeneros();
}
