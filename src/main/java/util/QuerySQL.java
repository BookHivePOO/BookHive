package util;

public class QuerySQL {
    public static final String INSERIR_USUARIO = "INSERT INTO Usuario (idUsuario,nome, cpf, idCredencial) VALUES (?, ?, ?, ?)";

    public static final String INSERIR_USUARIO_COM_ENDERECO = "INSERT INTO Usuario (idUsuario,nome, cpf, idCredencial, idEndereco) VALUES (?, ?, ?, ?, ?)";

    public static final String COUNT_USUARIO_POR_EMAIL = "SELECT COUNT(*) FROM Credencial WHERE email = ?";

    public static final String SELECT_USUARIO_POR_EMAIL_SENHA = "SELECT * FROM Credencial WHERE email = ? AND senha = ?";

    public static final  String INSERIR_CREDENCIAL = "INSERT INTO Credencial (idCredencial, email, senha, ativo) VALUES (?, ?, ?, ?)";

    public static final String INSERIR_LIVRO = "INSERT INTO Livro (titulo, autor, descricao, numeroPaginas, idGenero, estado, preco, foto, idUsuarioVenda) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String SELECIONAR_LIVROS_POR_NOME = "SELECT * FROM Livro WHERE titulo LIKE ?";

    public static final String SELECIONAR_LIVROS_POR_GENERO = "SELECT * FROM Livro WHERE idGenero = ?";
    public static final String SELECIONAR_GENERO_POR_DESCRICAO = "SELECT idGenero FROM Genero WHERE genero = ?";

    public static final String INSERIR_GENERO = "INSERT INTO Genero (genero) VALUES (?)";

    public static final String SELECIONAR_TODOS_GENEROS = "SELECT * FROM Genero";

}
