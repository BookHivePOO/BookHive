package model;

public class Credencial {
    private final long idCredencial;
    private final String email;
    private final String senha;
    private final boolean ativo;

    /**
     * Cria um objeto Credencial.
     *
     * @param idCredencial ID da credencial
     * @param email        e-mail da credencial
     * @param senha        senha da credencial
     * @param ativo        indica se a credencial está ativa
     */
    public Credencial(long idCredencial, String email, String senha, boolean ativo) {
        this.idCredencial = idCredencial;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
    }

    /**
     * Retorna o ID da credencial.
     *
     * @return ID da credencial
     */
    public long getIdCredencial() {
        return idCredencial;
    }

    /**
     * Retorna o e-mail da credencial.
     *
     * @return e-mail da credencial
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retorna a senha da credencial.
     *
     * @return senha da credencial
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Verifica se a credencial está ativa.
     *
     * @return true se a credencial estiver ativa, false caso contrário
     */
    public boolean isAtivo() {
        return ativo;
    }

}
