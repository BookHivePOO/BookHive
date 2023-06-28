package model;

public class Credencial {
    private final long idCredencial;
    private final String email;
    private final String senha;
    private final boolean ativo;

    public Credencial(long idCredencial, String email, String senha, boolean ativo) {
        this.idCredencial = idCredencial;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
    }

    public long getIdCredencial() {
        return idCredencial;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

}
