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

/**
 * Classe responsável por realizar operações de acesso aos dados relacionadas a Gêneros no banco de dados.
 */
public class GeneroDAO implements IGeneroDAO {
    private static final String MENSAGEM_SUCESSO_CADASTRO = "Gênero cadastrado com sucesso!";
    private static final String FALHA_CADASTRO = "Falha ao cadastrar o gênero.";

    private Connection connection;

    /**
     * Construtor da classe GeneroDAO.
     * Inicializa a conexão com o banco de dados.
     */
    public GeneroDAO() {
        try {
            connection = SQLConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtém o ID de um gênero a partir da descrição.
     *
     * @param genero a descrição do gênero a ser pesquisado
     * @return o ID do gênero correspondente, ou -1 se o gênero não for encontrado
     */
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

    /**
     * Cadastra um novo gênero no banco de dados.
     *
     * @param genero o nome do gênero a ser cadastrado
     */
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

    /**
     * Lista todos os gêneros cadastrados no banco de dados.
     *
     * @return uma lista de objetos Genero representando os gêneros cadastrados
     */
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

    /**
     * Obtém o nome de um gênero a partir do seu ID.
     *
     * @param idGenero o ID do gênero a ser pesquisado
     * @return o nome do gênero correspondente, ou null se o gênero não for encontrado
     */
    @Override
    public String obterNomeGeneroPorId(int idGenero) {
        try {
            String sql = QuerySQL.SELECIONAR_GENERO_POR_ID;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idGenero);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("genero");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Retorna null se o gênero não for encontrado
    }

}
