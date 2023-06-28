package application.swing;

import dao.UsuarioDAO;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class TelaCadastro extends JFrame {

    private JTextField campoNome;
    private JTextField campoCPF;
    private JTextField campoEmail;
    private JPasswordField campoSenha;
    private JButton botaoCadastrar;
    private JLabel mensagemErro;
    private TelaInicial telaInicial;

    public TelaCadastro() {

        // Configurações da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cadastro");
        setSize(500, 500);
        setLocationRelativeTo(null);

        // Painel principal com layout GridBagLayout
        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 20, 10, 20);

        // Campos de texto: nome, CPF, email e senha
        campoNome = new JTextField(30);
        campoCPF = new JTextField(30);
        campoEmail = new JTextField(30);
        campoSenha = new JPasswordField(30);

        // Botão "Cadastrar"
        botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(e -> cadastrarUsuario());

        // Mensagem de erro (exibida se o cadastro falhar)
        mensagemErro = new JLabel();

        // Adicione os componentes ao painel principal
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelPrincipal.add(new JLabel("Nome:"), gbc);
        gbc.gridy = 1;
        painelPrincipal.add(new JLabel("CPF:"), gbc);
        gbc.gridy = 2;
        painelPrincipal.add(new JLabel("Email:"), gbc);
        gbc.gridy = 3;
        painelPrincipal.add(new JLabel("Senha:"), gbc);
        gbc.gridy = 0;
        gbc.gridx = 1;
        painelPrincipal.add(campoNome, gbc);
        gbc.gridy = 1;
        painelPrincipal.add(campoCPF, gbc);
        gbc.gridy = 2;
        painelPrincipal.add(campoEmail, gbc);
        gbc.gridy = 3;
        painelPrincipal.add(campoSenha, gbc);
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        painelPrincipal.add(botaoCadastrar, gbc);
        gbc.gridy = 5;
        painelPrincipal.add(mensagemErro, gbc);

        // Adicione o painel principal à janela
        getContentPane().add(painelPrincipal, BorderLayout.CENTER);

        // Exibe a janela
        setVisible(true);
    }

    private void cadastrarUsuario() {
        String nome = campoNome.getText();
        long cpf = Long.parseLong(campoCPF.getText());
        String email = campoEmail.getText();
        String senha = new String(campoSenha.getPassword());

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean cadastro = usuarioDAO.cadastrarUsuario(nome, cpf, email, senha);

        if (cadastro) {
            TelaLogin telaLogin = new TelaLogin();
            setVisible(false);
        } else {
            mensagemErro.setText("Credenciais inválidas. Por favor, verifique seu email e senha.");
        }
    }
}
