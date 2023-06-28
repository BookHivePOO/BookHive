package util;

public class QuerySQL {
    public static final String INSERIR_USUARIO = "INSERT INTO Usuario (nome, cpf, idCredencial) VALUES (?, ?, ?)";

    public static final String INSERIR_USUARIO_COM_ENDERECO = "INSERT INTO Usuario (nome, cpf, idCredencial, idEndereco) VALUES (?, ?, ?, ?)";

    public static final String COUNT_USUARIO_POR_EMAIL = "SELECT COUNT(*) FROM Credencial WHERE email = ?";

    public static final String COUNT_USUARIO_POR_EMAIL_SENHA = "SELECT COUNT(*) FROM Credencial WHERE email = ? AND senha = ?";

    public static final  String INSERIR_CREDENCIAL = "INSERT INTO Credencial (idCredencial, email, senha, ativo) VALUES (?, ?, ?, ?)";
}
