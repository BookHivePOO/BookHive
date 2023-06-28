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

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public double getPreco() {
        return preco;
    }

    public String getFoto() {
        return foto;
    }

    public Long getIdUsuarioVenda() {
        return idUsuarioVenda;
    }

    public String getEstado() {
        return estado;
    }

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
