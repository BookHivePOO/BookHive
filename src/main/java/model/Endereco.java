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

    public int getIdEndereco() {
        return idEndereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public long getCep() {
        return cep;
    }
}