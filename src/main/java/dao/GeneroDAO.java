package dao;

import conection.SQLConnection;
import interfaces.IGeneroDAO;
import model.Genero;
import util.QuerySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneroDAO implements IGeneroDAO {
    private static final String MENSAGEM_SUCESSO_CADASTRO = "Gênero cadastrado com sucesso!";
    private static final String FALHA_CADASTRO = "Falha ao cadastrar o gênero.";

    private Connection connection;

    public GeneroDAO() {
        try {
            connection = SQLConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int obterIdGenero(String genero) {
        try {
            String sql = QuerySQL.SELECIONAR_GENERO_POR_DESCRICAO;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, genero);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("idGenero");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Retorna -1 se o gênero não for encontrado
    }

    @Override
    public void cadastrarGenero(String genero) {
        try {
            int idGenero = obterIdGenero(genero);
            if (idGenero != -1) {
                return; // Gênero já existe, não é necessário cadastrar novamente
            }

            String sql = QuerySQL.INSERIR_GENERO;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, genero);
            statement.executeUpdate();
            statement.close();

            System.out.println(MENSAGEM_SUCESSO_CADASTRO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Genero> listarGeneros() {
        List<Genero> generos = new ArrayList<>();

        try {
            String sql = QuerySQL.SELECIONAR_TODOS_GENEROS;
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idGenero = resultSet.getInt("idGenero");
                String genero = resultSet.getString("genero");

                Genero newGenero = new Genero(idGenero, genero);
                generos.add(newGenero);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generos;
    }
}
