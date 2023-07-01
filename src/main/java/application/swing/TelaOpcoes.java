package application.swing;

import util.RelatorioGenerator;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents the options screen of the application.
 */
public class TelaOpcoes extends JFrame {

    /**
     * Creates a new instance of the TelaOpcoes class.
     *
     * @param idUsuario  the user ID
     * @param nome       the user name
     */
    public TelaOpcoes(long idUsuario, String nome) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Opções");
        setSize(600, 500);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton botaoCadastrarLivro = criarBotao("Cadastrar Livro");
        botaoCadastrarLivro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaCadastroLivro(idUsuario, nome);
                setVisible(false);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        painelPrincipal.add(botaoCadastrarLivro, gbc);

        JButton botaoComprarLivro = criarBotao("Comprar livro");
        botaoComprarLivro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaComprarLivro(idUsuario, nome);
                setVisible(false);
            }
        });
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        painelPrincipal.add(botaoComprarLivro, gbc);

        JButton botaoHistoricoVendas = criarBotao("Histórico de Vendas");
        botaoHistoricoVendas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaHistoricoVendas(idUsuario, nome);
            }
        });
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        painelPrincipal.add(botaoHistoricoVendas, gbc);

        JButton botaoHistoricoCompras = criarBotao("Histórico de Compras");
        botaoHistoricoCompras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaHistoricoCompras(idUsuario, nome);
            }
        });
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        painelPrincipal.add(botaoHistoricoCompras, gbc);

        JButton botaoRelatorio = criarBotao("Gerar Relatório");
        botaoRelatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RelatorioGenerator.gerarRelatorioCompleto();
                JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso! O arquivo de relatório foi salvo na pasta 'relatorios/result'.");
            }
        });
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        painelPrincipal.add(botaoRelatorio, gbc);

        getContentPane().add(painelPrincipal);

        setVisible(true);
    }

    /**
     * Creates a button with the given text and a preferred size of 200x40 pixels.
     *
     * @param texto  the button text
     * @return the created button
     */
    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setPreferredSize(new Dimension(200, 40));
        return botao;
    }
}
