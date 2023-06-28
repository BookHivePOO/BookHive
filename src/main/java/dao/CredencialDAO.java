package dao;

import conection.SQLConnection;
import model.Credencial;
import util.QuerySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe responsável por realizar operações de acesso aos dados relacionadas a Credenciais no banco de dados.
 */
public class CredencialDAO {
    private Connection connection;

    /**
     * Construtor da classe CredencialDAO.
     * Inicializa a conexão com o banco de dados.
     */
    public CredencialDAO() {
        try {
            connection = SQLConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cria uma nova credencial com o email e senha fornecidos.
     *
     * @param email o email associado à credencial
     * @param senha a senha associada à credencial
     * @return a nova instância de Credencial criada
     */
    public Credencial criarNovaCredencial(String email, String senha) {
        int novoIdCredencial = (int) (Math.random() * 1000);
        Credencial credencial = new Credencial(novoIdCredencial, email, senha, true);

        try {
            String sql = QuerySQL.INSERIR_CREDENCIAL;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, credencial.getIdCredencial());
            statement.setString(2, email);
            statement.setString(3, senha);
            statement.setBoolean(4, true);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return credencial;
    }
}

