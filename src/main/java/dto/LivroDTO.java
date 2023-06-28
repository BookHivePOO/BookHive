package dto;

import dao.GeneroDAO;

/**
 * Classe que representa um Livro na forma de objeto de transferência de dados (DTO).
 */
public class LivroDTO {
    private final int id;
    private final String titulo;
    private final String autor;
    private final String descricao;
    private final int numeroPaginas;
    private final String genero;
    private final double preco;
    private final String foto;
    private final Long idUsuarioVenda;
    private final String estado;

    /**
     * Construtor da classe LivroDTO.
     *
     * @param id             ID do livro
     * @param titulo         Título do livro
     * @param autor          Autor do livro
     * @param descricao      Descrição do livro
     * @param numeroPaginas  Número de páginas do livro
     * @param genero         Gênero do livro
     * @param preco          Preço do livro
     * @param foto           URL da foto do livro
     * @param idUsuarioVenda ID do usuário que está vendendo o livro
     * @param estado         Estado do livro (novo, usado, etc.)
     */
    public LivroDTO(int id, String titulo, String autor, String descricao, int numeroPaginas, int genero, double preco, String foto, Long idUsuarioVenda, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.descricao = descricao;
        this.numeroPaginas = numeroPaginas;
        this.genero = convertGenero(genero);
        this.preco = preco;
        this.foto = foto;
        this.idUsuarioVenda = idUsuarioVenda;
        this.estado = estado;
    }

    private String convertGenero(int idGenero) {
        return new GeneroDAO().obterNomeGeneroPorId(idGenero);
    }

    /**
     * Obtém o título do livro.
     *
     * @return Título do livro
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Obtém o autor do livro.
     *
     * @return Autor do livro
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Obtém a descrição do livro.
     *
     * @return Descrição do livro
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Obtém o número de páginas do livro.
     *
     * @return Número de páginas do livro
     */
    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    /**
     * Obtém o gênero do livro.
     *
     * @return Gênero do livro
     */
    public String getIdGenero() {
        return genero;
    }

    /**
     * Obtém o preço do livro.
     *
     * @return Preço do livro
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Obtém a URL da foto do livro.
     *
     * @return URL da foto do livro
     */
    public String getFoto() {
        return foto;
    }

    /**
     * Obtém o ID do usuário que está vendendo o livro.
     *
     * @return ID do usuário de venda
     */
    public Long getIdUsuarioVenda() {
        return idUsuarioVenda;
    }

    /**
     * Obtém o estado do livro.
     *
     * @return Estado do livro
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Imprime os detalhes do livro.
     */
    public void imprimirLivro() {
        System.out.println("Título: " + titulo);
        System.out.println("Id do livro: " + id);
        System.out.println("Autor: " + autor);
        System.out.println("Descrição: " + descricao);
        System.out.println("Número de Páginas: " + numeroPaginas);
        System.out.println("Gênero: " + genero);
        System.out.println("Preço: " + preco);
        System.out.println("Foto: " + foto);
        System.out.println("ID do Usuário de Venda: " + idUsuarioVenda);
        System.out.println("Estado: " + estado);
    }

    /**
     * Obtém o ID do livro.
     *
     * @return ID do livro
     */
    public int getId() {
        return id;
    }
}
