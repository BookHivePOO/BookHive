package model;

public class Transacao {
    private int idTransacao;
    private int idCompra;
    private int idVenda;
    private int idLivro;
    private int idPagamento;
    private int idEnderecoEntrega;
    private double totalPagar;

    public Transacao(int idCompra, int idVenda, int idLivro, int idPagamento, int idEnderecoEntrega, double totalPagar) {
        this.idCompra = idCompra;
        this.idVenda = idVenda;
        this.idLivro = idLivro;
        this.idPagamento = idPagamento;
        this.idEnderecoEntrega = idEnderecoEntrega;
        this.totalPagar = totalPagar;
    }

    /**
     * Retorna o ID da transação.
     *
     * @return ID da transação
     */
    public int getIdTransacao() {
        return idTransacao;
    }

    /**
     * Define o ID da transação.
     *
     * @param idTransacao ID da transação
     */
    public void setIdTransacao(int idTransacao) {
        this.idTransacao = idTransacao;
    }

    /**
     * Retorna o ID da compra.
     *
     * @return ID da compra
     */
    public int getIdCompra() {
        return idCompra;
    }

    /**
     * Define o ID da compra.
     *
     * @param idCompra ID da compra
     */
    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    /**
     * Retorna o ID da venda.
     *
     * @return ID da venda
     */
    public int getIdVenda() {
        return idVenda;
    }

    /**
     * Define o ID da venda.
     *
     * @param idVenda ID da venda
     */
    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    /**
     * Retorna o ID do livro.
     *
     * @return ID do livro
     */
    public int getIdLivro() {
        return idLivro;
    }

    /**
     * Define o ID do livro.
     *
     * @param idLivro ID do livro
     */
    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    /**
     * Retorna o ID do pagamento.
     *
     * @return ID do pagamento
     */
    public int getIdPagamento() {
        return idPagamento;
    }

    /**
     * Define o ID do pagamento.
     *
     * @param idPagamento ID do pagamento
     */
    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    /**
     * Retorna o ID do endereço de entrega.
     *
     * @return ID do endereço de entrega
     */
    public int getIdEnderecoEntrega() {
        return idEnderecoEntrega;
    }

    /**
     * Define o ID do endereço de entrega.
     *
     * @param idEnderecoEntrega ID do endereço de entrega
     */
    public void setIdEnderecoEntrega(int idEnderecoEntrega) {
        this.idEnderecoEntrega = idEnderecoEntrega;
    }

    /**
     * Retorna o valor total a pagar.
     *
     * @return valor total a pagar
     */
    public double getTotalPagar() {
        return totalPagar;
    }

    /**
     * Define o valor total a pagar.
     *
     * @param totalPagar valor total a pagar
     */
    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

}
