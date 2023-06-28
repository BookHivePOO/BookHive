package services;

import interfaces.IUsuarioDAO;
import model.Usuario;

public class AutenticacaoService {
    private final IUsuarioDAO usuarioDAO;

    public AutenticacaoService(IUsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario realizarLogin(String email, String senha) {
        return usuarioDAO.realizarLogin(email, senha);
    }
}
