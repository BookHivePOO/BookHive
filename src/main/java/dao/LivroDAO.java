package dao;

import conection.SQLConnection;
import dto.LivroDTO;
import interfaces.ILivroDAO;
import model.Livro;
import util.QuerySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por realizar operações de acesso aos dados relacionadas a Livros no banco de dados.
 */
public class LivroDAO implements ILivroDAO {
    private static final String MENSAGEM_SUCESSO_CADASTRO = "Livro cadastrado com sucesso!";
    private static final String FALHA_CADASTRO = "Falha ao cadastrar o livro.";

    private Connection connection;

    /**
     * Construtor da classe LivroDAO.
     * Inicializa a conexão com o banco de dados.
     */
    public LivroDAO() {
        try {
            connection = SQLConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cadastra um novo livro no banco de dados.
     *
     * @param titulo           o título do livro
     * @param autor            o autor do livro
     * @param descricao        a descrição do livro
     * @param numeroPaginas    o número de páginas do livro
     * @param genero           o gênero do livro
     * @param preco            o preço do livro
     * @param foto             a foto do livro
     * @param idUsuarioVenda   o ID do usuário que está vendendo o livro
     * @param estado           o estado do livro
     * @return true se o livro for cadastrado com sucesso, false caso contrário
     */
    @Override
    public boolean cadastrarLivro(String titulo, String autor, String descricao, int numeroPaginas, String genero, double preco, String foto, Long idUsuarioVenda, String estado) {
        try {
            int idGenero;

            // Verificar se o gênero já existe no banco de dados
            GeneroDAO generoDAO = new GeneroDAO();
            idGenero = generoDAO.obterIdGenero(genero);

            if (idGenero == -1) {
                // Se o gênero não existir, cadastrar na tabela Genero
                generoDAO.cadastrarGenero(genero);
                idGenero = generoDAO.obterIdGenero(genero);
            }

            Livro livro = new Livro(titulo, autor, descricao, numeroPaginas, idGenero, preco, foto, idUsuarioVenda, estado);
            String sql = QuerySQL.INSERIR_LIVRO;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, livro.getTitulo());
            statement.setString(2, livro.getAutor());
            statement.setString(3, livro.getDescricao());
            statement.setInt(4, livro.getNumeroPaginas());
            statement.setInt(5, livro.getIdGenero());
            statement.setString(6, livro.getEstado());
            statement.setDouble(7, livro.getPreco());
            statement.setString(8, livro.getFoto());
            statement.setLong(9, livro.getIdUsuarioVenda());
            statement.executeUpdate();
            statement.close();

            System.out.println(MENSAGEM_SUCESSO_CADASTRO);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Pesquisa livros por nome.
     *
     * @param nome o nome do livro a ser pesquisado
     * @return uma lista de objetos LivroDTO contendo os livros encontrados
     */
    @Override
    public List<LivroDTO> pesquisarLivrosPorNome(String nome) {
        List<LivroDTO> livrosEncontrados = new ArrayList<>();

        try {
            String sql = QuerySQL.SELECIONAR_LIVROS_POR_NOME;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + nome + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idLivro = resultSet.getInt("idLivro");
                String titulo = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                String descricao = resultSet.getString("descricao");
                int numeroPaginas = resultSet.getInt("numeroPaginas");
                int idGenero = resultSet.getInt("idGenero");
                String estado = resultSet.getString("estado");
                double preco = resultSet.getDouble("preco");
                String foto = resultSet.getString("foto");
                long idUsuarioVenda = resultSet.getLong("idUsuarioVenda");

                LivroDTO livro = new LivroDTO(idLivro, titulo, autor, descricao, numeroPaginas, idGenero, preco, foto, idUsuarioVenda, estado);
                livrosEncontrados.add(livro);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livrosEncontrados;
    }

    /**
     * Lista os livros por gênero.
     *
     * @param genero o gênero dos livros a serem listados
     * @return uma lista de objetos LivroDTO contendo os livros encontrados
     */
    @Override
    public List<LivroDTO> listarLivrosPorGenero(String genero) {
        List<LivroDTO> livrosEncontrados = new ArrayList<>();

        try {
            GeneroDAO generoDAO = new GeneroDAO();
            int idGeneroSearch = generoDAO.obterIdGenero(genero);

            String sql = QuerySQL.SELECIONAR_LIVROS_POR_GENERO;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idGeneroSearch);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idLivro = resultSet.getInt("idLivro");
                String titulo = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                String descricao = resultSet.getString("descricao");
                int numeroPaginas = resultSet.getInt("numero_paginas");
                int idGenero = resultSet.getInt("idGenero");
                String estado = resultSet.getString("estado");
                double preco = resultSet.getDouble("preco");
                String foto = resultSet.getString("foto");
                long idUsuarioVenda = resultSet.getLong("idUsuarioVenda");

                LivroDTO livro = new LivroDTO(idLivro, titulo, autor, descricao, numeroPaginas, idGenero, preco, foto, idUsuarioVenda, estado);
                livrosEncontrados.add(livro);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livrosEncontrados;
    }

    /**
     * Lista todos os livros.
     *
     * @return uma lista de objetos LivroDTO contendo todos os livros encontrados
     */
    @Override
    public List<LivroDTO> listarTodosLivros() {
        List<LivroDTO> livrosEncontrados = new ArrayList<>();

        try {
            String sql = QuerySQL.SELECIONAR_TODOS_LIVROS;
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idLivro = resultSet.getInt("idLivro");
                String titulo = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                String descricao = resultSet.getString("descricao");
                int numeroPaginas = resultSet.getInt("numeroPaginas");
                int idGenero = resultSet.getInt("idGenero");
                String estado = resultSet.getString("estado");
                double preco = resultSet.getDouble("preco");
                String foto = resultSet.getString("foto");
                long idUsuarioVenda = resultSet.getLong("idUsuarioVenda");

                LivroDTO livro = new LivroDTO(idLivro, titulo, autor, descricao, numeroPaginas, idGenero, preco, foto, idUsuarioVenda, estado);
                livrosEncontrados.add(livro);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livrosEncontrados;
    }

    /**
     * Pesquisa um livro por ID.
     *
     * @param idLivro o ID do livro a ser pesquisado
     * @return um objeto LivroDTO contendo as informações do livro encontrado, ou null se o livro não for encontrado
     */
    @Override
    public LivroDTO pesquisarLivroPorId(int idLivro) {
        try {
            String sql = QuerySQL.SELECIONAR_LIVRO_POR_ID;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idLivro);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String titulo = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                String descricao = resultSet.getString("descricao");
                int numeroPaginas = resultSet.getInt("numeroPaginas");
                int idGenero = resultSet.getInt("idGenero");
                String estado = resultSet.getString("estado");
                double preco = resultSet.getDouble("preco");
                String foto = resultSet.getString("foto");
                long idUsuarioVenda = resultSet.getLong("idUsuarioVenda");

                LivroDTO livro = new LivroDTO(idLivro, titulo, autor, descricao, numeroPaginas, idGenero, preco, foto, idUsuarioVenda, estado);
                resultSet.close();
                statement.close();
                return livro;
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Retorna null se o livro não for encontrado
    }

    /**
     * Remove um livro do banco de dados.
     *
     * @param idLivro o ID do livro a ser removido
     */
    @Override
    public void removerLivro(int idLivro) {
        try {
            String sql = QuerySQL.ATUALIZAR_LIVRO_COMPRADO;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, false);
            statement.setInt(2, idLivro);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
