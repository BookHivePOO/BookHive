package dao;

import conection.SQLConnection;
import interfaces.IUsuarioDAO;
import model.Credencial;
import model.Usuario;
import org.bouncycastle.crypto.generators.BCrypt;
import org.jasypt.util.password.StrongPasswordEncryptor;
import util.QuerySQL;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt.*;
import util.Validacoes;

public class UsuarioDAO implements IUsuarioDAO {
    private static final String EMAIL_CADASTRADO_ERROR_MENSAGEM = "E-mail já cadastrado. Por favor, tente outro e-mail.";
    private static final String MENSAGEM_SUCESSO_CADASTRO = "Usuário cadastrado com sucesso!";

    public static final String MENSAGEM_DADOS_INVALIDOS = "Não foi possivel finalizar o cadastro. Dado inserido " +
            "incorretamente, verifique os mesmos e reenvie a solicitação";
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

        if(!Validacoes.validarCadastroUsuario(nome,cpf,email,senha)){
            System.out.println(MENSAGEM_DADOS_INVALIDOS);
            return false;

        }

        CredencialDAO credencialDAO = new CredencialDAO();
        if (credencialDAO.verificarEmailExistente(email)) {
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
    public void atualizarIdEndereco(int idUsuario, int idEndereco) {
        try {
            String sql = QuerySQL.ATUALIZAR_ENDERECO_USUARIO;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idEndereco);
            statement.setInt(2, idUsuario);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Lista todos os usuários no banco de dados.
     *
     * @return uma lista contendo todos os usuários
     */
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            String sql = QuerySQL.LISTAR_TODOS_USUARIOS;
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idUsuario = resultSet.getInt("idUsuario");
                String nome = resultSet.getString("nome");
                long cpf = resultSet.getLong("cpf");
                int idCredencial = resultSet.getInt("idCredencial");
                long idEndereco = resultSet.getLong("idEndereco");

                Usuario usuario = new Usuario(idUsuario, nome, cpf, (long) idCredencial, idEndereco);
                usuarios.add(usuario);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }
}
