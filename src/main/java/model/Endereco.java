package model;

public class Endereco {
    private final int idEndereco;
    private final String logradouro;
    private final int numero;
    private final String complemento;
    private final String bairro;
    private final String cidade;
    private final String estado;
    private final long cep;

    public Endereco(int idEndereco, String logradouro, int numero, String complemento, String bairro, String cidade, String estado, long cep) {
        this.idEndereco = idEndereco;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    /**
     * Retorna o ID do endereço.
     *
     * @return ID do endereço
     */
    public int getIdEndereco() {
        return idEndereco;
    }

    /**
     * Retorna o logradouro do endereço.
     *
     * @return logradouro do endereço
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * Retorna o número do endereço.
     *
     * @return número do endereço
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Retorna o complemento do endereço.
     *
     * @return complemento do endereço
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * Retorna o bairro do endereço.
     *
     * @return bairro do endereço
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * Retorna a cidade do endereço.
     *
     * @return cidade do endereço
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * Retorna o estado do endereço.
     *
     * @return estado do endereço
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Retorna o CEP do endereço.
     *
     * @return CEP do endereço
     */
    public long getCep() {
        return cep;
    }
}
