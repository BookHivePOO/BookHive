package dao;

import conection.SQLConnection;
import model.Credencial;
import util.QuerySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CredencialDAO {
    private Connection connection;

    public CredencialDAO() {
        try {
            connection = SQLConnection.getConnection();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public Credencial criarNovaCredencial(String email, String senha) {
        int novoIdCredencial = (int) (Math.random() * 1000);
        Credencial credencial = new Credencial(novoIdCredencial, email, senha, true);

        try {
            String sql = QuerySQL.INSERIR_CREDENCIAL;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, credencial.getIdCredencial());
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

