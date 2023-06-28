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

    public String getNome(){
        return nome;
    }

    public long getCpf(){
        return cpf;
    }

    public Long getIdCredencial() {
        return idCredencial;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public long getId() {
        return id;
    }
}
