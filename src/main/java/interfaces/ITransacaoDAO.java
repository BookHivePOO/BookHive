package interfaces;

import dto.LivroDTO;
import dto.TransacaoDTO;

import java.util.List;

public interface ITransacaoDAO {

    /**
     * Efetua uma compra de um livro no sistema.
     *
     * @param idLivro       ID do livro a ser comprado
     * @param idEndereco    ID do endereço de entrega
     * @param idUsuario     ID do usuário que está realizando a compra
     * @param idPagamento   ID do pagamento utilizado na compra
     * @return true se a compra for efetuada com sucesso, false caso contrário
     */
    boolean efetuarCompra(int idLivro, int idEndereco, int idUsuario, int idPagamento);

    /**
     * Calcula o valor total de um livro considerando descontos, promoções, etc.
     *
     * @param livro objeto LivroDTO representando o livro
     * @return o valor total do livro
     */
    double calculaValorTotal(LivroDTO livro);

    /**
     * Pesquisa as transações de compras realizadas por um usuário específico.
     *
     * @param idUsuario ID do usuário
     * @return uma lista de objetos TransacaoDTO representando as transações de compra encontradas
     */
    List<TransacaoDTO> pesquisarComprasPorIdUsuario(long idUsuario);

    /**
     * Pesquisa as transações de vendas realizadas por um usuário específico.
     *
     * @param idUsuario ID do usuário
     * @return uma lista de objetos TransacaoDTO representando as transações de venda encontradas
     */
    List<TransacaoDTO> pesquisarVendasPorIdUsuario(long idUsuario);
}
