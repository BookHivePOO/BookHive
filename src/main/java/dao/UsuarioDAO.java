package dao;
import conection.SQLConnection;
import interfaces.IUsuarioDAO;
import model.Credencial;
import model.Usuario;
import util.QuerySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO implements IUsuarioDAO {
    private static final String EMAIL_CADASTRADO_ERROR_MENSAGEM = "E-mail já cadastrado. Por favor, tente outro e-mail.";
    private static final String MENSAGEM_SUCESSO_CADASTRO = "Usuário cadastrado com sucesso!";
    private static final String MENSAGEM_SUCESSO_LOGIN = "Login realizado com sucesso!";
    private static final String CREDENCIAIS_INVALIDAS_ERROR_MENSAGEM = "E-mail ou senha incorretos. Por favor, verifique suas credenciais.";

    private Connection connection;
    private CredencialDAO credencialDAO;

    public UsuarioDAO() {
        try {
            connection = SQLConnection.getConnection();
            credencialDAO = new CredencialDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean cadastrarUsuario(String nome, long cpf, String email, String senha) {
        if (verificarEmailExistente(email)) {
            System.out.println(EMAIL_CADASTRADO_ERROR_MENSAGEM);
            return false;
        }

        Credencial credencial = credencialDAO.criarNovaCredencial(email, senha);

        Usuario usuario = new Usuario(nome, cpf, credencial, null);

        try {
            String sql;
            if (usuario.getEndereco() != null) {
                sql = QuerySQL.INSERIR_USUARIO_COM_ENDERECO;
            } else {
                sql = QuerySQL.INSERIR_USUARIO;
            }
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setLong(2, usuario.getCpf());
            statement.setInt(3, credencial.getIdCredencial());
            if (usuario.getEndereco() != null) {
                statement.setInt(4, usuario.getEndereco().getIdEndereco());
            }
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
    public boolean verificarEmailExistente(String email) {
        try {
            String sql = QuerySQL.COUNT_USUARIO_POR_EMAIL;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean realizarLogin(String email, String senha) {
        try {
            String sql = QuerySQL.COUNT_USUARIO_POR_EMAIL_SENHA;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, senha);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    System.out.println(MENSAGEM_SUCESSO_LOGIN);
                    return true;
                }
            }

            System.out.println(CREDENCIAIS_INVALIDAS_ERROR_MENSAGEM);
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
