package model;

public class Usuario {
    private final String nome;
    private final long cpf;
    private final Credencial credencial;
    private final Endereco endereco;

    public Usuario(String nome, long cpf, Credencial credencial, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.credencial = credencial;
        this.endereco = endereco;
    }

    public String getNome(){
        return nome;
    }

    public long getCpf(){
        return cpf;
    }

    public Credencial getCredencial(){
        return credencial;
    }

    public Endereco getEndereco(){
        return endereco;
    }
}
