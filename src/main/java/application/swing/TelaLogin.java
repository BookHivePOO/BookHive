package application.swing;

import dao.CredencialDAO;
import dao.UsuarioDAO;
import model.Usuario;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * Classe que representa a tela de login da aplicação.
 */
public class TelaLogin extends JFrame {

    private JTextField campoEmail;
    private JPasswordField campoSenha;
    private JButton botaoLogin;
    private JLabel mensagemErro;
    private TelaInicial telaInicial;

    /**
     * Construtor da classe TelaLogin.
     * Cria e exibe a tela de login.
     */
    public TelaLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login");
        setSize(700, 700);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 20, 10, 20);

        campoEmail = new JTextField(30);
        campoSenha = new JPasswordField(30);

        botaoLogin = new JButton("Login");
        botaoLogin.addActionListener(e -> realizarLogin());

        mensagemErro = new JLabel();
        mensagemErro.setForeground(Color.RED);

        gbc.gridx = 0;
        gbc.gridy = 0;
        painelPrincipal.add(new JLabel("Email:"), gbc);
        gbc.gridy = 1;
        painelPrincipal.add(new JLabel("Senha:"), gbc);
        gbc.gridy = 0;
        gbc.gridx = 1;
        painelPrincipal.add(campoEmail, gbc);
        gbc.gridy = 1;
        painelPrincipal.add(campoSenha, gbc);
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        painelPrincipal.add(botaoLogin, gbc);
        gbc.gridy = 3;
        painelPrincipal.add(mensagemErro, gbc);

        JPanel painelBotoes = new JPanel();
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(e -> voltarTelaInicial());
        painelBotoes.add(botaoVoltar);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(painelPrincipal, BorderLayout.CENTER);
        getContentPane().add(painelBotoes, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Realiza o login do usuário.
     * Obtém as informações de email e senha informadas nos campos de texto.
     * Verifica as credenciais no banco de dados.
     * Se o login for bem-sucedido, redireciona para a tela de opções.
     * Caso contrário, exibe uma mensagem de erro na tela.
     */
    private void realizarLogin() {
        String email = campoEmail.getText();
        String senha = new String(campoSenha.getPassword());

        CredencialDAO credencialDAO = new CredencialDAO();
        Usuario usuario = null;

        try {
            usuario = credencialDAO.realizarLogin(email, senha);
        } catch (Exception e) {
            mensagemErro.setText("Erro ao realizar o login. Por favor, tente novamente.");
        }

        if (usuario != null) {
            TelaOpcoes telaOpcoes = new TelaOpcoes(usuario.getId(), usuario.getNome());
            setVisible(false);
        } else {
            mensagemErro.setText("Credenciais inválidas. Por favor, verifique seu email e senha.");
        }
    }

    /**
     * Volta para a tela inicial.
     */
    private void voltarTelaInicial() {
        TelaInicial telaInicial = new TelaInicial();
        setVisible(false);
    }
}
