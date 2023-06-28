package interfaces;

import model.Pagamento;

import java.util.Date;

public interface IPagamentoDAO {
    /**
     * Cadastra um novo pagamento no sistema.
     *
     * @param nomeCartao      nome do titular do cartão
     * @param numeroCartao    número do cartão de crédito
     * @param bandeira        bandeira do cartão de crédito
     * @param dataValidade    data de validade do cartão de crédito
     * @param codigoSeguranca código de segurança do cartão de crédito
     * @return objeto Pagamento representando o pagamento cadastrado, ou null em caso de falha
     */
    Pagamento cadastrarPagamento(String nomeCartao, String numeroCartao, String bandeira, Date dataValidade, long codigoSeguranca);
}
