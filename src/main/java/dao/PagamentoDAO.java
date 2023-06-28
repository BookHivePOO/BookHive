package dao;

import conection.SQLConnection;
import interfaces.IPagamentoDAO;
import model.Pagamento;
import util.QuerySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PagamentoDAO implements IPagamentoDAO {
    private static final String MENSAGEM_SUCESSO_CADASTRO = "Pagamento cadastrado com sucesso!";
    private static final String FALHA_CADASTRO = "Falha ao cadastrar o pagamento.";

    private Connection connection;

    public PagamentoDAO() {
        try {
            connection = SQLConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cadastra um novo pagamento no banco de dados.
     *
     * @param nomeCartao     o nome do cartão de pagamento
     * @param numeroCartao   o número do cartão de pagamento
     * @param bandeira       a bandeira do cartão de pagamento
     * @param dataValidade   a data de validade do cartão de pagamento
     * @param codigoSeguranca o código de segurança do cartão de pagamento
     * @return o objeto Pagamento cadastrado, ou null em caso de falha
     */
    @Override
    public Pagamento cadastrarPagamento(String nomeCartao, String numeroCartao, String bandeira, Date dataValidade, long codigoSeguranca) {
        try {
            String sql = QuerySQL.INSERIR_PAGAMENTO;
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, nomeCartao);
            statement.setString(2, numeroCartao);
            statement.setString(3, bandeira);
            statement.setDate(4, new java.sql.Date(dataValidade.getTime()));
            statement.setLong(5, codigoSeguranca);
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idPagamento = generatedKeys.getInt(1);
                Pagamento novoPagamento = new Pagamento(idPagamento, nomeCartao, numeroCartao, bandeira, dataValidade, codigoSeguranca);
                System.out.println(MENSAGEM_SUCESSO_CADASTRO);
                return novoPagamento;
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println(FALHA_CADASTRO);
            e.printStackTrace();
        }

        return null;
    }
}
