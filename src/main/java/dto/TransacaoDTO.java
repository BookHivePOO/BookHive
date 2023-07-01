package dto;

/**
 * Classe que representa uma transação na forma de objeto de transferência de dados (DTO).
 */
public class TransacaoDTO {
    private final int idTransacao;
    private final int idCompra;
    private final int idVenda;
    private final int idLivro;
    private final int idPagamento;
    private final int idEnderecoEntrega;
    private final double totalPagar;

    /**
     * Construtor da classe TransacaoDTO.
     *
     * @param idTransacao       ID da transação
     * @param idCompra          ID da compra
     * @param idVenda           ID da venda
     * @param idLivro           ID do livro
     * @param idPagamento       ID do pagamento
     * @param idEnderecoEntrega ID do endereço de entrega
     * @param totalPagar        Valor total a pagar
     */
    public TransacaoDTO(int idTransacao, int idCompra, int idVenda, int idLivro, int idPagamento, int idEnderecoEntrega, double totalPagar) {
        this.idTransacao = idTransacao;
        this.idCompra = idCompra;
        this.idVenda = idVenda;
        this.idLivro = idLivro;
        this.idPagamento = idPagamento;
        this.idEnderecoEntrega = idEnderecoEntrega;
        this.totalPagar = totalPagar;
    }

    /**
     * Obtém o ID da transação.
     *
     * @return ID da transação
     */
    public int getIdTransacao() {
        return idTransacao;
    }

    /**
     * Obtém o ID da compra.
     *
     * @return ID da compra
     */
    public int getIdCompra() {
        return idCompra;
    }


    /**
     * Obtém o ID da venda.
     *
     * @return ID da venda
     */
    public int getIdVenda() {
        return idVenda;
    }

    /**
     * Obtém o ID do livro.
     *
     * @return ID do livro
     */
    public int getIdLivro() {
        return idLivro;
    }

    /**
     * Obtém o ID do pagamento.
     *
     * @return ID do pagamento
     */
    public int getIdPagamento() {
        return idPagamento;
    }
    /**
     * Obtém o ID do endereço de entrega.
     *
     * @return ID do endereço de entrega
     */
    public int getIdEnderecoEntrega() {
        return idEnderecoEntrega;
    }

    /**
     * Obtém o valor total a pagar.
     *
     * @return Valor total a pagar
     */
    public double getTotalPagar() {
        return totalPagar;
    }

    /**
     * Imprime os detalhes da transação.
     */
    public void imprimirTransacao() {
        System.out.println("ID da Transação: " + idTransacao);
        System.out.println("ID da Compra: " + idCompra);
        System.out.println("ID da Venda: " + idVenda);
        System.out.println("ID do Livro: " + idLivro);
        System.out.println("ID do Pagamento: " + idPagamento);
        System.out.println("ID do Endereço de Entrega: " + idEnderecoEntrega);
        System.out.println("Total a Pagar: R$" + totalPagar);
    }
}
