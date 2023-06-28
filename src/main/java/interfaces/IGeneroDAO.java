package interfaces;

import model.Genero;

import java.util.List;

public interface IGeneroDAO {
    /**
     * Obtém o ID de um gênero a partir do seu nome.
     *
     * @param genero nome do gênero
     * @return ID do gênero encontrado, ou -1 se não for encontrado
     */
    int obterIdGenero(String genero);

    /**
     * Cadastra um novo gênero no sistema.
     *
     * @param genero nome do gênero a ser cadastrado
     */
    void cadastrarGenero(String genero);

    /**
     * Retorna a lista de todos os gêneros cadastrados no sistema.
     *
     * @return lista de objetos Genero representando os gêneros cadastrados
     */
    List<Genero> listarGeneros();

    /**
     * Obtém o nome de um gênero a partir do seu ID.
     *
     * @param idGenero ID do gênero
     * @return nome do gênero encontrado, ou null se não for encontrado
     */
    String obterNomeGeneroPorId(int idGenero);
}
