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

        int novoId = (int) (Math.random() * 1000);
        Usuario usuario = new Usuario(novoId, nome, cpf, credencial.getIdCredencial(), null);

        try {
            String sql;
            if (usuario.getIdEndereco() != null) {
                sql = QuerySQL.INSERIR_USUARIO_COM_ENDERECO;
            } else {
                sql = QuerySQL.INSERIR_USUARIO;
            }
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, usuario.getId());
            statement.setString(2, usuario.getNome());
            statement.setLong(3, usuario.getCpf());
            statement.setLong(4, credencial.getIdCredencial());
            if (usuario.getIdEndereco() != null) {
                statement.setLong(5, usuario.getIdEndereco());
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
    public Usuario realizarLogin(String email, String senha) {
        try {
            String sql = QuerySQL.SELECT_USUARIO_POR_EMAIL_SENHA;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, senha);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idCredencial = resultSet.getInt("idCredencial");

                // Buscar os dados do usuário com base no ID da credencial
                String sqlUsuario = "SELECT * FROM Usuario WHERE idCredencial = ?";
                PreparedStatement statementUsuario = connection.prepareStatement(sqlUsuario);
                statementUsuario.setInt(1, idCredencial);
                ResultSet resultSetUsuario = statementUsuario.executeQuery();

                if (resultSetUsuario.next()) {
                    int idUsuario = resultSetUsuario.getInt("idUsuario");
                    String nome = resultSetUsuario.getString("nome");
                    long cpf = resultSetUsuario.getLong("cpf");
                    long idEndereco = resultSetUsuario.getLong("idEndereco");

                    return new Usuario(idUsuario, nome, cpf, (long) idCredencial, idEndereco);
                }

                resultSetUsuario.close();
                statementUsuario.close();
            }

            System.out.println(CREDENCIAIS_INVALIDAS_ERROR_MENSAGEM);
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Retorna null se o login falhar
    }



}
