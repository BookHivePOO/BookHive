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
     * Atualiza o ID do endereço de um usuário.
     *
     * @param idUsuario   ID do usuário
     * @param idEndereco  ID do endereço a ser atualizado
     */
    void atualizarIdEndereco(int idUsuario, int idEndereco);
}
