package interfaces;

import model.Usuario;

public interface IUsuarioDAO {

    /**
     * Cadastra um novo usuário no sistema.
     *
     * @param nome  nome do usuário
     * @param cpf   CPF do usuário
     * @param email email do usuário
     * @param senha senha do usuário
     * @return true se o cadastro for realizado com sucesso, false caso contrário
     */
    boolean cadastrarUsuario(String nome, long cpf, String email, String senha);

    /**
     * Verifica se o e-mail já está cadastrado no sistema.
     *
     * @param email e-mail a ser verificado
     * @return true se o e-mail já estiver cadastrado, false caso contrário
     */
    boolean verificarEmailExistente(String email);

    /**
     * Realiza o login de um usuário no sistema.
     *
     * @param email  e-mail do usuário
     * @param senha  senha do usuário
     * @return objeto Usuario representando o usuário logado, ou null se o login falhar
     */
    Usuario realizarLogin(String email, String senha);

    /**
     * Atualiza o ID do endereço de um usuário.
     *
     * @param idUsuario   ID do usuário
     * @param idEndereco  ID do endereço a ser atualizado
     */
    void atualizarIdEndereco(int idUsuario, int idEndereco);
}
