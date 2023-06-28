-- Criação da tabela "Genero"
CREATE TABLE Genero (
  idGenero INT AUTO_INCREMENT PRIMARY KEY,
  genero VARCHAR(255)
);

-- Criação da tabela "Credencial"
CREATE TABLE Credencial (
  idCredencial INT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(255),
  senha VARCHAR(255),
  ativo BOOLEAN
);

-- Criação da tabela "Endereco"
CREATE TABLE Endereco (
  idEndereco INT AUTO_INCREMENT PRIMARY KEY,
  logradouro VARCHAR(255),
  numero INT,
  complemento VARCHAR(255),
  bairro VARCHAR(255),
  cidade VARCHAR(255),
  estado VARCHAR(255),
  cep BIGINT
);

-- Criação da tabela "Usuario"
CREATE TABLE Usuario (
  idUsuario INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(255),
  cpf BIGINT,
  idCredencial INT,
  idEndereco INT,
  FOREIGN KEY (idCredencial) REFERENCES Credencial(idCredencial),
  FOREIGN KEY (idEndereco) REFERENCES Endereco(idEndereco)
);

-- Criação da tabela "Livro"
CREATE TABLE Livro (
  idLivro INT AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(255),
  autor VARCHAR(255),
  descricao VARCHAR(255),
  numeroPaginas BIGINT,
  idGenero INT,
  estado VARCHAR(255),
  preco DOUBLE,
  foto VARCHAR(255),
  idUsuarioVenda INT,
  disponivel BOOLEAN DEFAULT TRUE,
  FOREIGN KEY (idGenero) REFERENCES Genero(idGenero),
  FOREIGN KEY (idUsuarioVenda) REFERENCES Usuario(idUsuario)
);

-- Criação da tabela "Pagamento"
CREATE TABLE Pagamento (
  idPagamento INT AUTO_INCREMENT PRIMARY KEY,
  nomeCartao VARCHAR(255),
  numeroCartao VARCHAR(255),
  bandeira VARCHAR(255),
  dataValidade DATE,
  codigoSeguranca BIGINT
);

-- Criação da tabela "Transacao"
CREATE TABLE Transacao (
  idTransacao INT AUTO_INCREMENT PRIMARY KEY,
  idCompra INT,
  idVenda INT,
  idLivro INT,
  idPagamento INT,
  idEnderecoEntrega INT,
  totalPagar DOUBLE,
  FOREIGN KEY (idCompra) REFERENCES Usuario(idUsuario),
  FOREIGN KEY (idVenda) REFERENCES Usuario(idUsuario),
  FOREIGN KEY (idLivro) REFERENCES Livro(idLivro),
  FOREIGN KEY (idPagamento) REFERENCES Pagamento(idPagamento),
  FOREIGN KEY (idEnderecoEntrega) REFERENCES Endereco(idEndereco)
);
