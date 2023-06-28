package interfaces;

import model.Livro;

import java.util.List;

public interface ILivroDAO {
    boolean cadastrarLivro(String titulo, String autor, String descricao, int numeroPaginas, String genero, double preco, String foto, Long idUsuarioVenda, String estado);

    List<Livro> pesquisarLivrosPorNome(String nome);

    List<Livro> listarLivrosPorGenero(String genero);
    // Outros métodos para operações relacionadas a livros
}
