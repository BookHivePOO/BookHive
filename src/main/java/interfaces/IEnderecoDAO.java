package interfaces;

import model.Endereco;

public interface IEnderecoDAO {
    /**
     * Cadastra um novo endereço no sistema.
     *
     * @param logradouro  Logradouro do endereço
     * @param numero      Número do endereço
     * @param complemento Complemento do endereço
     * @param bairro      Bairro do endereço
     * @param cidade      Cidade do endereço
     * @param estado      Estado do endereço
     * @param cep         CEP do endereço
     * @param idUsuario   ID do usuário relacionado ao endereço
     * @return ID do endereço cadastrado
     */
    int cadastrarEndereco(String logradouro, int numero, String complemento, String bairro, String cidade, String estado, long cep, int idUsuario);

    /**
     * Pesquisa um endereço pelo seu ID.
     *
     * @param idEndereco ID do endereço a ser pesquisado
     * @return Objeto Endereco contendo as informações do endereço encontrado, ou null caso não seja encontrado
     */
    Endereco pesquisarEnderecoPorId(int idEndereco);
}
