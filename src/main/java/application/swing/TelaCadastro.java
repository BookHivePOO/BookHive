package application.swing;

import dao.UsuarioDAO;

import javax.swing.*;
import java.awt.*;

/**
 * Classe que representa a tela de cadastro de usuários.
 */
public class TelaCadastro extends JFrame {

    private JTextField campoNome;
    private JTextField campoCPF;
    private JTextField campoEmail;
    private JPasswordField campoSenha;
    private JButton botaoCadastrar;
    private JLabel mensagemErro;
    private TelaInicial telaInicial;

    /**
     * Construtor da classe TelaCadastro.
     * Cria e exibe a tela de cadastro de usuários.
     */
    public TelaCadastro() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cadastro");
        setSize(700, 700);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 20, 10, 20);

        campoNome = new JTextField(30);
        campoCPF = new JTextField(30);
        campoEmail = new JTextField(30);
        campoSenha = new JPasswordField(30);

        botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(e -> cadastrarUsuario());

        mensagemErro = new JLabel();
        mensagemErro.setForeground(Color.RED);

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
     * Realiza o cadastro de um novo usuário.
     * Obtém as informações de nome, CPF, email e senha informadas nos campos de texto.
     * Chama o método cadastrarUsuario() do DAO para realizar o cadastro.
     * Se o cadastro for bem-sucedido, redireciona para a tela de login.
     * Caso contrário, exibe uma mensagem de erro na tela.
     */
    private void cadastrarUsuario() {
        String nome = campoNome.getText();
        long cpf = Long.parseLong(campoCPF.getText());
        String email = campoEmail.getText();
        String senha = new String(campoSenha.getPassword());

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean cadastro = false;

        try {
            cadastro = usuarioDAO.cadastrarUsuario(nome, cpf, email, senha);
        } catch (Exception e) {
            mensagemErro.setText("Erro ao realizar o cadastro. Por favor, tente novamente.");
        }

        if (cadastro) {
            TelaLogin telaLogin = new TelaLogin();
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
