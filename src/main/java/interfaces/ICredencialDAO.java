package interfaces;

import model.Credencial;
import model.Usuario;

import java.util.List;

public interface ICredencialDAO {
    Credencial criarNovaCredencial(String email, String senha);

    Usuario realizarLogin(String email, String senha);

    List<Credencial> listarCredenciais();

    boolean verificarEmailExistente(String email);
}
