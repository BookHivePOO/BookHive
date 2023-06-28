package dao;

import conection.SQLConnection;
import dto.LivroDTO;
import dto.TransacaoDTO;
import interfaces.ITransacaoDAO;
import util.QuerySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDAO implements ITransacaoDAO {
    private static final String MENSAGEM_SUCESSO_COMPRA = "Compra efetuada com sucesso!";
    private static final String FALHA_COMPRA = "Falha ao efetuar a compra.";

    private Connection connection;

    public TransacaoDAO() {
        try {
            connection = SQLConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Efetua a compra de um livro pelo usuário.
     *
     * @param idLivro    o ID do livro a ser comprado
     * @param idEndereco o ID do endereço de entrega
     * @param idUsuario  o ID do usuário que está realizando a compra
     * @param idPagamento o ID do pagamento associado à transação
     * @return true se a compra foi efetuada com sucesso, false caso contrário
     */
    @Override
    public boolean efetuarCompra(int idLivro, int idEndereco, int idUsuario, int idPagamento) {
        try {
            LivroDAO livroDAO = new LivroDAO();
            LivroDTO livro = livroDAO.pesquisarLivroPorId(idLivro);

            double valorTotal = calculaValorTotal(livro);

            // Cadastrar a transação
            String sql = QuerySQL.INSERIR_TRANSACAO;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idUsuario);
            statement.setInt(2, idLivro);
            statement.setInt(3, idPagamento);
            statement.setInt(4, idEndereco);
            statement.setDouble(5, valorTotal);
            statement.executeUpdate();
            statement.close();

            System.out.println(MENSAGEM_SUCESSO_COMPRA);

            livroDAO.removerLivro(idLivro);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Calcula o valor total da transação considerando o preço do livro e possíveis descontos.
     *
     * @param livro o livro a ser comprado
     * @return o valor total da transação
     */
    @Override
    public double calculaValorTotal(LivroDTO livro) {
        // Implemente o cálculo do valor total considerando o preço do livro, descontos, etc.
        return livro.getPreco();
    }

    /**
     * Pesquisa as compras realizadas por um determinado usuário.
     *
     * @param idUsuario o ID do usuário
     * @return uma lista de TransacaoDTO contendo as informações das compras
     */
    @Override
    public List<TransacaoDTO> pesquisarComprasPorIdUsuario(long idUsuario) {
        List<TransacaoDTO> comprasEncontradas = new ArrayList<>();

        try {
            String sql = QuerySQL.PESQUISAR_COMPRAS_POR_ID_USUARIO;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, idUsuario);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idTransacao = resultSet.getInt("idTransacao");
                int idCompra = resultSet.getInt("idCompra");
                int idVenda = resultSet.getInt("idVenda");
                int idLivro = resultSet.getInt("idLivro");
                int idPagamento = resultSet.getInt("idPagamento");
                int idEnderecoEntrega = resultSet.getInt("idEnderecoEntrega");
                double totalPagar = resultSet.getDouble("totalPagar");

                TransacaoDTO transacao = new TransacaoDTO(idTransacao, idCompra, idVenda, idLivro, idPagamento, idEnderecoEntrega, totalPagar);
                comprasEncontradas.add(transacao);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comprasEncontradas;
    }

    /**
     * Pesquisa as vendas realizadas por um determinado usuário.
     *
     * @param idUsuario o ID do usuário
     * @return uma lista de TransacaoDTO contendo as informações das vendas
     */
    @Override
    public List<TransacaoDTO> pesquisarVendasPorIdUsuario(long idUsuario) {
        List<TransacaoDTO> vendasEncontradas = new ArrayList<>();

        try {
            String sql = QuerySQL.PESQUISAR_VENDAS_POR_ID_USUARIO;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, idUsuario);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idTransacao = resultSet.getInt("idTransacao");
                int idCompra = resultSet.getInt("idCompra");
                int idVenda = resultSet.getInt("idVenda");
                int idLivro = resultSet.getInt("idLivro");
                int idPagamento = resultSet.getInt("idPagamento");
                int idEnderecoEntrega = resultSet.getInt("idEnderecoEntrega");
                double totalPagar = resultSet.getDouble("totalPagar");

                TransacaoDTO transacao = new TransacaoDTO(idTransacao, idCompra, idVenda, idLivro, idPagamento, idEnderecoEntrega, totalPagar);
                vendasEncontradas.add(transacao);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vendasEncontradas;
    }
}
