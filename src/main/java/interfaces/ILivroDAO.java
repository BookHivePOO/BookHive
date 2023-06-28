package interfaces;

import dto.LivroDTO;

import java.util.List;

public interface ILivroDAO {
    /**
     * Cadastra um novo livro no sistema.
     *
     * @param titulo         título do livro
     * @param autor          autor do livro
     * @param descricao      descrição do livro
     * @param numeroPaginas  número de páginas do livro
     * @param genero         gênero do livro
     * @param preco          preço do livro
     * @param foto           URL da foto do livro
     * @param idUsuarioVenda ID do usuário responsável pela venda do livro
     * @param estado         estado do livro (ex: novo, usado)
     * @return true se o cadastro for bem-sucedido, false caso contrário
     */
    boolean cadastrarLivro(String titulo, String autor, String descricao, int numeroPaginas, String genero, double preco, String foto, Long idUsuarioVenda, String estado);

    /**
     * Pesquisa livros pelo nome.
     *
     * @param nome nome do livro ou parte do nome
     * @return lista de objetos LivroDTO representando os livros encontrados
     */
    List<LivroDTO> pesquisarLivrosPorNome(String nome);

    /**
     * Lista livros de um determinado gênero.
     *
     * @param genero gênero dos livros
     * @return lista de objetos LivroDTO representando os livros encontrados
     */
    List<LivroDTO> listarLivrosPorGenero(String genero);

    /**
     * Lista todos os livros cadastrados no sistema.
     *
     * @return lista de objetos LivroDTO representando todos os livros cadastrados
     */
    List<LivroDTO> listarTodosLivros();

    /**
     * Pesquisa um livro pelo ID.
     *
     * @param idLivro ID do livro
     * @return objeto LivroDTO representando o livro encontrado, ou null se não for encontrado
     */
    LivroDTO pesquisarLivroPorId(int idLivro);

    /**
     * Remove um livro do sistema.
     *
     * @param idLivro ID do livro a ser removido
     */
    void removerLivro(int idLivro);

    // Outros métodos para operações relacionadas a livros
}
