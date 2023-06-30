package dao;

import conection.SQLConnection;
import interfaces.ICredencialDAO;
import model.Credencial;
import model.Usuario;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.mindrot.jbcrypt.BCrypt;
import util.QuerySQL;
import util.Validacoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por realizar operações de acesso aos dados relacionadas a Credenciais no banco de dados.
 */
public class CredencialDAO implements ICredencialDAO {

    public static final String MENSAGEM_ERRO_FORMATO_EMAIL = "Email com formato Invalido";

    private static final String CREDENCIAIS_INVALIDAS_ERROR_MENSAGEM = "E-mail ou senha incorretos. Por favor, verifique suas credenciais.";


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
    @Override
    public Credencial criarNovaCredencial(String email, String senha) {

        int novoIdCredencial = (int) (Math.random() * 1000);
        Credencial credencial = new Credencial(novoIdCredencial, email, null, true); // A senha será definida posteriormente

        // Criptografar a senha
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        String senhaCriptografada = passwordEncryptor.encryptPassword(senha);

        try {
            String sql = QuerySQL.INSERIR_CREDENCIAL;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, credencial.getIdCredencial());
            statement.setString(2, email);
            statement.setString(3, senhaCriptografada); // Usar a senha criptografada
            statement.setBoolean(4, true);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return credencial;
    }

    @Override
    public Usuario realizarLogin(String email, String senha) {
        try {
            String sql = QuerySQL.SELECT_USUARIO_POR_EMAIL;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idCredencial = resultSet.getInt("idCredencial");
                String senhaArmazenada = resultSet.getString("senha");

                // Verificar se a senha fornecida pelo usuário corresponde à senha armazenada
                StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
                if (passwordEncryptor.checkPassword(senha, senhaArmazenada)) {
                    String sqlUsuario = QuerySQL.BUSCAR_USUARIO_POR_ID;
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
                } else {
                    System.out.println(CREDENCIAIS_INVALIDAS_ERROR_MENSAGEM);
                }
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ...

    /**
     * Lista todas as credenciais no banco de dados.
     *
     * @return uma lista contendo todas as credenciais
     */
    @Override
    public List<Credencial> listarCredenciais() {
        List<Credencial> credenciais = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Credencial";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idCredencial = resultSet.getInt("idCredencial");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                boolean ativo = resultSet.getBoolean("ativo");

                Credencial credencial = new Credencial(idCredencial, email, senha, ativo);
                credenciais.add(credencial);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return credenciais;
    }

    @Override
    public boolean verificarEmailExistente(String email) {
        try {
            String sql = QuerySQL.VERIFICA_EMAIL;
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
}

