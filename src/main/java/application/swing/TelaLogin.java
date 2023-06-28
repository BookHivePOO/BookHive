package application.swing;

import dao.UsuarioDAO;
import model.Usuario;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class TelaLogin extends JFrame {

    private JTextField campoEmail;
    private JPasswordField campoSenha;
    private JButton botaoLogin;
    private JLabel mensagemErro;
    private TelaInicial telaInicial;

    public TelaLogin() {

        // Configurações da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login");
        setSize(500, 500);
        setLocationRelativeTo(null);

        // Painel principal com layout GridBagLayout
        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 20, 10, 20);

        // Campos de texto: email e senha
        campoEmail = new JTextField(30);
        campoSenha = new JPasswordField(30);

        // Botão "Login"
        botaoLogin = new JButton("Login");
        botaoLogin.addActionListener(e -> realizarLogin());

        // Mensagem de erro (exibida se o login falhar)
        mensagemErro = new JLabel();

        // Adicione os componentes ao painel principal
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

        // Adicione o painel principal à janela
        getContentPane().add(painelPrincipal, BorderLayout.CENTER);

        // Exibe a janela
        setVisible(true);
    }

    private void realizarLogin() {
        String email = campoEmail.getText();
        String senha = new String(campoSenha.getPassword());

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.realizarLogin(email, senha);

        if (usuario != null) {
            // Login bem-sucedido
            // Redirecionar para outra tela, por exemplo:
            TelaOpcoes telaOpcoes = new TelaOpcoes();
            setVisible(false);
        } else {
            // Login falhou
            mensagemErro.setText("Credenciais inválidas. Por favor, verifique seu email e senha.");
        }
    }
}
