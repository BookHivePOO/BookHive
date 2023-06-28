package dao;

import conection.SQLConnection;
import interfaces.IEnderecoDAO;
import model.Endereco;
import util.QuerySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe responsável por realizar operações de acesso aos dados relacionadas a Endereços no banco de dados.
 */
public class EnderecoDAO implements IEnderecoDAO {
    private static final String MENSAGEM_SUCESSO_CADASTRO = "Endereço cadastrado com sucesso!";
    private static final String FALHA_CADASTRO = "Falha ao cadastrar o endereço.";

    private Connection connection;

    /**
     * Construtor da classe EnderecoDAO.
     * Inicializa a conexão com o banco de dados.
     */
    public EnderecoDAO() {
        try {
            connection = SQLConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cadastra um novo endereço no banco de dados.
     *
     * @param logradouro o logradouro do endereço
     * @param numero o número do endereço
     * @param complemento o complemento do endereço
     * @param bairro o bairro do endereço
     * @param cidade a cidade do endereço
     * @param estado o estado do endereço
     * @param cep o CEP do endereço
     * @param idUsuario o ID do usuário associado ao endereço
     * @return o ID do endereço cadastrado, ou -1 em caso de falha
     */
    @Override
    public int cadastrarEndereco(String logradouro, int numero, String complemento, String bairro, String cidade, String estado, long cep, int idUsuario) {
        try {
            String sql = QuerySQL.INSERIR_ENDERECO;
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, logradouro);
            statement.setInt(2, numero);
            statement.setString(3, complemento);
            statement.setString(4, bairro);
            statement.setString(5, cidade);
            statement.setString(6, estado);
            statement.setLong(7, cep);
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            int idEndereco = -1;
            if (resultSet.next()) {
                idEndereco = resultSet.getInt(1);
            }

            resultSet.close();
            statement.close();

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.atualizarIdEndereco(idUsuario, idEndereco);

            System.out.println(MENSAGEM_SUCESSO_CADASTRO);
            return idEndereco;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Pesquisa um endereço no banco de dados pelo ID.
     *
     * @param idEndereco o ID do endereço a ser pesquisado
     * @return a instância de Endereco correspondente ao ID, ou null se o endereço não for encontrado
     */
    @Override
    public Endereco pesquisarEnderecoPorId(int idEndereco) {
        try {
            String sql = QuerySQL.SELECIONAR_ENDERECO_POR_ID;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idEndereco);
            ResultSet resultSet = statement.executeQuery();

            Endereco endereco = null;
            if (resultSet.next()) {
                String logradouro = resultSet.getString("logradouro");
                int numero = resultSet.getInt("numero");
                String complemento = resultSet.getString("complemento");
                String bairro = resultSet.getString("bairro");
                String cidade = resultSet.getString("cidade");
                String estado = resultSet.getString("estado");
                long cep = resultSet.getLong("cep");

                endereco = new Endereco(idEndereco, logradouro, numero, complemento, bairro, cidade, estado, cep);
            }

            resultSet.close();
            statement.close();
            return endereco;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
