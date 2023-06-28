package application.swing;

import javax.swing.*;
import java.awt.*;

public class TelaInicial extends JFrame {

    private final JButton botaoEntrar;
    private final JButton botaoCadastrar;

    public TelaInicial() {
        // Configurações da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Tela Inicial");

        // Painel principal com layout GridBagLayout
        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Botão "Entrar"
        botaoEntrar = new JButton("Entrar");
        botaoEntrar.addActionListener(e -> abrirTelaLogin());
        botaoEntrar.setPreferredSize(new Dimension(150, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelPrincipal.add(botaoEntrar, gbc);

        // Botão "Cadastrar"
        botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(e -> abrirTelaCadastro());
        botaoCadastrar.setPreferredSize(new Dimension(150, 30));
        gbc.gridy = 1;
        painelPrincipal.add(botaoCadastrar, gbc);

        // Adiciona o painel principal à janela
        getContentPane().add(painelPrincipal);

        // Exibe a janela
        setVisible(true);
    }

    private void abrirTelaLogin() {
        // Código para abrir a tela de login
        TelaLogin telaLogin = new TelaLogin();
        setVisible(false);
    }

    private void abrirTelaCadastro() {
        // Código para abrir a tela de cadastro
        TelaCadastro telaCadastro = new TelaCadastro();
        setVisible(false);
    }

    public static void main(String[] args) {
        TelaInicial telaInicial = new TelaInicial();
    }
}
