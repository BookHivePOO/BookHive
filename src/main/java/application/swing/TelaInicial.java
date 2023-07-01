package application.swing;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the initial screen of the application.
 */
public class TelaInicial extends JFrame {

    private final JButton botaoEntrar;
    private final JButton botaoCadastrar;

    /**
     * Creates a new instance of the TelaInicial class.
     */
    public TelaInicial() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Tela Inicial");

        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        botaoEntrar = new JButton("Entrar");
        botaoEntrar.addActionListener(e -> abrirTelaLogin());
        botaoEntrar.setPreferredSize(new Dimension(150, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelPrincipal.add(botaoEntrar, gbc);

        botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(e -> abrirTelaCadastro());
        botaoCadastrar.setPreferredSize(new Dimension(150, 30));
        gbc.gridy = 1;
        painelPrincipal.add(botaoCadastrar, gbc);

        getContentPane().add(painelPrincipal);

        setVisible(true);
    }

    /**
     * Opens the login screen.
     */
    private void abrirTelaLogin() {
        TelaLogin telaLogin = new TelaLogin();
        setVisible(false);
    }

    /**
     * Opens the registration screen.
     */
    private void abrirTelaCadastro() {
        TelaCadastro telaCadastro = new TelaCadastro();
        setVisible(false);
    }

    /**
     * Entry point of the application.
     *
     * @param args  the command-line arguments
     */
    public static void main(String[] args) {
        TelaInicial telaInicial = new TelaInicial();
    }
}
