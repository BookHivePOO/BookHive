package util;

/**
 * Classe que contém as consultas SQL utilizadas no sistema.
 */
public class QuerySQL {
    /**
     * Query para inserir um novo usuário sem endereço.
     */
    public static final String INSERIR_USUARIO = "INSERT INTO Usuario (idUsuario, nome, cpf, idCredencial) VALUES (?, ?, ?, ?)";

    /**
     * Query para inserir um novo usuário com endereço.
     */
    public static final String INSERIR_USUARIO_COM_ENDERECO = "INSERT INTO Usuario (idUsuario, nome, cpf, idCredencial, idEndereco) VALUES (?, ?, ?, ?, ?)";

    /**
     * Query para verificar a existência de um e-mail.
     */
    public static final String VERIFICA_EMAIL = "SELECT COUNT(*) FROM Credencial WHERE email = ?";

    /**
     * Query para selecionar um usuário por e-mail e senha.
     */
    public static final String SELECT_USUARIO_POR_EMAIL_SENHA = "SELECT * FROM Credencial WHERE email = ? AND senha = ?";

    /**
     * Query para inserir uma nova credencial.
     */
    public static final String INSERIR_CREDENCIAL = "INSERT INTO Credencial (idCredencial, email, senha, ativo) VALUES (?, ?, ?, ?)";

    /**
     * Query para inserir um novo livro.
     */
    public static final String INSERIR_LIVRO = "INSERT INTO Livro (titulo, autor, descricao, numeroPaginas, idGenero, estado, preco, foto, idUsuarioVenda) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    /**
     * Query para selecionar todos os livros.
     */
    public static final String SELECIONAR_TODOS_LIVROS = "SELECT * FROM Livro WHERE disponivel = 1";

    /**
     * Query para selecionar livros por nome.
     */
    public static final String SELECIONAR_LIVROS_POR_NOME = "SELECT * FROM Livro WHERE titulo LIKE ? AND disponivel = 1";

    /**
     * Query para selecionar livros por gênero.
     */
    public static final String SELECIONAR_LIVROS_POR_GENERO = "SELECT * FROM Livro WHERE idGenero = ? AND disponivel = 1";

    /**
     * Query para selecionar o ID de um gênero por descrição.
     */
    public static final String SELECIONAR_GENERO_POR_DESCRICAO = "SELECT idGenero FROM Genero WHERE genero = ?";

    /**
     * Query para inserir um novo gênero.
     */
    public static final String INSERIR_GENERO = "INSERT INTO Genero (genero) VALUES (?)";

    /**
     * Query para selecionar todos os gêneros.
     */
    public static final String SELECIONAR_TODOS_GENEROS = "SELECT * FROM Genero";

    /**
     * Query para selecionar o nome de um gênero por ID.
     */
    public static final String SELECIONAR_GENERO_POR_ID = "SELECT genero FROM Genero WHERE idGenero = ?";

    /**
     * Query para selecionar um livro por ID.
     */
    public static final String SELECIONAR_LIVRO_POR_ID = "SELECT * FROM Livro WHERE idLivro = ?";

    /**
     * Query para inserir um novo pagamento.
     */
    public static final String INSERIR_PAGAMENTO = "INSERT INTO Pagamento (nomeCartao, numeroCartao, bandeira, dataValidade, codigoSeguranca) VALUES (?, ?, ?, ?, ?)";

    /**
     * Query para inserir uma nova transação.
     */
    public static final String INSERIR_TRANSACAO = "INSERT INTO Transacao (idCompra, idVenda, idLivro, idPagamento, idEnderecoEntrega, totalPagar) VALUES (?, ?, ?, ?, ?, ?)";

    /**
     * Query para pesquisar compras por ID do usuário.
     */
    public static final String PESQUISAR_COMPRAS_POR_ID_USUARIO = "SELECT * FROM Transacao WHERE idCompra = ?";

    /**
     * Query para pesquisar vendas por ID do usuário.
     */
    public static final String PESQUISAR_VENDAS_POR_ID_USUARIO = "SELECT * FROM Transacao WHERE idVenda = ?";

    /**
     * Query para inserir um novo endereço.
     */
    public static final String INSERIR_ENDERECO = "INSERT INTO Endereco (logradouro, numero, complemento, bairro, cidade, estado, cep) VALUES (?, ?, ?, ?, ?, ?, ?)";

    /**
     * Query para selecionar um endereço por ID.
     */
    public static final String SELECIONAR_ENDERECO_POR_ID = "SELECT * FROM Endereco WHERE idEndereco = ?";

    /**
     * Query para atualizar o ID do endereço de um usuário.
     */
    public static final String ATUALIZAR_ENDERECO_USUARIO = "UPDATE Usuario SET idEndereco = ? WHERE idUsuario = ?";

    /**
     * Query para remover um livro.
     */
    public static final String ATUALIZAR_LIVRO_COMPRADO = "UPDATE Livro SET disponivel = ? WHERE idLivro = ?";
}
