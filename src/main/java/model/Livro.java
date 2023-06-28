package model;

public class Livro {
    private final String titulo;
    private final String autor;
    private final String descricao;
    private final int numeroPaginas;
    private final int idGenero;
    private final double preco;
    private final String foto;
    private final Long idUsuarioVenda;
    private final String estado;

    public Livro(String titulo, String autor, String descricao, int numeroPaginas, int idGenero, double preco, String foto, Long idUsuarioVenda, String estado) {
        this.titulo = titulo;
        this.autor = autor;
        this.descricao = descricao;
        this.numeroPaginas = numeroPaginas;
        this.idGenero = idGenero;
        this.preco = preco;
        this.foto = foto;
        this.idUsuarioVenda = idUsuarioVenda;
        this.estado = estado;
    }

    /**
     * Retorna o título do livro.
     *
     * @return título do livro
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Retorna o autor do livro.
     *
     * @return autor do livro
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Retorna a descrição do livro.
     *
     * @return descrição do livro
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna o número de páginas do livro.
     *
     * @return número de páginas do livro
     */
    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    /**
     * Retorna o ID do gênero do livro.
     *
     * @return ID do gênero do livro
     */
    public int getIdGenero() {
        return idGenero;
    }

    /**
     * Retorna o preço do livro.
     *
     * @return preço do livro
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Retorna a foto do livro.
     *
     * @return foto do livro
     */
    public String getFoto() {
        return foto;
    }

    /**
     * Retorna o ID do usuário responsável pela venda do livro.
     *
     * @return ID do usuário responsável pela venda do livro
     */
    public Long getIdUsuarioVenda() {
        return idUsuarioVenda;
    }

    /**
     * Retorna o estado do livro.
     *
     * @return estado do livro
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Imprime os detalhes do livro.
     */
    public void imprimirLivro() {
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Descrição: " + descricao);
        System.out.println("Número de Páginas: " + numeroPaginas);
        System.out.println("idGênero: " + idGenero);
        System.out.println("Preço: " + preco);
        System.out.println("Foto: " + foto);
        System.out.println("ID do Usuário de Venda: " + idUsuarioVenda);
        System.out.println("Estado: " + estado);
    }
}
