package services;

import interfaces.IUsuarioDAO;

public class AutenticacaoService {
    private final IUsuarioDAO usuarioDAO;

    public AutenticacaoService(IUsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public boolean realizarLogin(String email, String senha) {
        return usuarioDAO.realizarLogin(email, senha);
    }
}
