package model;

import java.util.Date;

public class Pagamento {
    private int idPagamento;
    private String nomeCartao;
    private String numeroCartao;
    private String bandeira;
    private Date dataValidade;
    private long codigoSeguranca;

    public Pagamento(int idPagamento, String nomeCartao, String numeroCartao, String bandeira, Date dataValidade, long codigoSeguranca) {
        this.idPagamento = idPagamento;
        this.nomeCartao = nomeCartao;
        this.numeroCartao = numeroCartao;
        this.bandeira = bandeira;
        this.dataValidade = dataValidade;
        this.codigoSeguranca = codigoSeguranca;
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
     * Retorna o nome do cartão.
     *
     * @return nome do cartão
     */
    public String getNomeCartao() {
        return nomeCartao;
    }

    /**
     * Define o nome do cartão.
     *
     * @param nomeCartao nome do cartão
     */
    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }

    /**
     * Retorna o número do cartão.
     *
     * @return número do cartão
     */
    public String getNumeroCartao() {
        return numeroCartao;
    }

    /**
     * Define o número do cartão.
     *
     * @param numeroCartao número do cartão
     */
    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    /**
     * Retorna a bandeira do cartão.
     *
     * @return bandeira do cartão
     */
    public String getBandeira() {
        return bandeira;
    }

    /**
     * Define a bandeira do cartão.
     *
     * @param bandeira bandeira do cartão
     */
    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    /**
     * Retorna a data de validade do cartão.
     *
     * @return data de validade do cartão
     */
    public Date getDataValidade() {
        return dataValidade;
    }

    /**
     * Define a data de validade do cartão.
     *
     * @param dataValidade data de validade do cartão
     */
    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    /**
     * Retorna o código de segurança do cartão.
     *
     * @return código de segurança do cartão
     */
    public long getCodigoSeguranca() {
        return codigoSeguranca;
    }

    /**
     * Define o código de segurança do cartão.
     *
     * @param codigoSeguranca código de segurança do cartão
     */
    public void setCodigoSeguranca(long codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }
}
