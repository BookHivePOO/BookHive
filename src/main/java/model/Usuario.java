package model;

public class Usuario {
    private final int id;
    private final String nome;
    private final long cpf;
    private final Long idCredencial;
    private final Long idEndereco;

    public Usuario(int id, String nome, long cpf, Long idCredencial, Long idEndereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.idCredencial = idCredencial;
        this.idEndereco = idEndereco;
    }

    /**
     * Retorna o nome do usuário.
     *
     * @return nome do usuário
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o CPF do usuário.
     *
     * @return CPF do usuário
     */
    public long getCpf() {
        return cpf;
    }

    /**
     * Retorna o ID da credencial do usuário.
     *
     * @return ID da credencial do usuário
     */
    public Long getIdCredencial() {
        return idCredencial;
    }

    /**
     * Retorna o ID do endereço do usuário.
     *
     * @return ID do endereço do usuário
     */
    public Long getIdEndereco() {
        return idEndereco;
    }

    /**
     * Retorna o ID do usuário.
     *
     * @return ID do usuário
     */
    public long getId() {
        return id;
    }
}
