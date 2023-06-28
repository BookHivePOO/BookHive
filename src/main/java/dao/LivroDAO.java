package dao;

import conection.SQLConnection;
import interfaces.ILivroDAO;
import model.Genero;
import model.Livro;
import util.QuerySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO implements ILivroDAO {
    private static final String MENSAGEM_SUCESSO_CADASTRO = "Livro cadastrado com sucesso!";
    private static final String FALHA_CADASTRO = "Falha ao cadastrar o livro.";

    private Connection connection;

    public LivroDAO() {
        try {
            connection = SQLConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    @Override
    public List<Livro> pesquisarLivrosPorNome(String nome) {
        List<Livro> livrosEncontrados = new ArrayList<>();

        try {
            String sql = QuerySQL.SELECIONAR_LIVROS_POR_NOME;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + nome + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String titulo = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                String descricao = resultSet.getString("descricao");
                int numeroPaginas = resultSet.getInt("numeroPaginas");
                int idGenero = resultSet.getInt("idGenero");
                String estado = resultSet.getString("estado");
                double preco = resultSet.getDouble("preco");
                String foto = resultSet.getString("foto");
                long idUsuarioVenda = resultSet.getLong("idUsuarioVenda");

                Livro livro = new Livro(titulo, autor, descricao, numeroPaginas, idGenero, preco, foto, idUsuarioVenda, estado);
                livrosEncontrados.add(livro);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livrosEncontrados;
    }

    @Override
    public List<Livro> listarLivrosPorGenero(String genero) {
        List<Livro> livrosEncontrados = new ArrayList<>();

        try {
            GeneroDAO generoDAO = new GeneroDAO();
            int idGeneroSearch = generoDAO.obterIdGenero(genero);

            String sql = QuerySQL.SELECIONAR_LIVROS_POR_GENERO;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idGeneroSearch);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String titulo = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                String descricao = resultSet.getString("descricao");
                int numeroPaginas = resultSet.getInt("numero_paginas");
                int idGenero = resultSet.getInt("idGenero");
                String estado = resultSet.getString("estado");
                double preco = resultSet.getDouble("preco");
                String foto = resultSet.getString("foto");
                long idUsuarioVenda = resultSet.getLong("idUsuarioVenda");

                Livro livro = new Livro(titulo, autor, descricao, numeroPaginas, idGenero, preco, foto, idUsuarioVenda, estado);
                livrosEncontrados.add(livro);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livrosEncontrados;
    }

}
