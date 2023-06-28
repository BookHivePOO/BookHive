package interfaces;

import model.Usuario;

public interface IUsuarioDAO {
    boolean cadastrarUsuario(String nome, long cpf, String email, String senha);
    boolean verificarEmailExistente(String email);
    boolean realizarLogin(String email, String senha);
}
