package application.swing;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaOpcoes extends JFrame {

    public TelaOpcoes() {
        // Configurações da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Opções");
        setSize(500, 500);
        setLocationRelativeTo(null);

        // Painel principal com layout GridBagLayout
        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Botão "Cadastrar Livro"
        JButton botaoCadastrarLivro = criarBotao("Cadastrar Livro");
        botaoCadastrarLivro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para a ação do botão "Cadastrar Livro"
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelPrincipal.add(botaoCadastrarLivro, gbc);

        // Botão "Pesquisar Livro por Nome"
        JButton botaoPesquisarLivro = criarBotao("Pesquisar Livro por Nome");
        botaoPesquisarLivro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para a ação do botão "Pesquisar Livro por Nome"
            }
        });
        gbc.gridy = 1;
        painelPrincipal.add(botaoPesquisarLivro, gbc);

        // Botão "Listar Livros por Gênero"
        JButton botaoListarLivros = criarBotao("Listar Livros por Gênero");
        botaoListarLivros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para a ação do botão "Listar Livros por Gênero"
            }
        });
        gbc.gridy = 2;
        painelPrincipal.add(botaoListarLivros, gbc);

        // Botão "Listar todos os livros"
        JButton botaoListarTodos = criarBotao("Listar todos os livros");
        botaoListarTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para a ação do botão "Listar todos os livros"
            }
        });
        gbc.gridy = 3;
        painelPrincipal.add(botaoListarTodos, gbc);

        // Botão "Comprar livro"
        JButton botaoComprarLivro = criarBotao("Comprar livro");
        botaoComprarLivro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para a ação do botão "Comprar livro"
            }
        });
        gbc.gridy = 4;
        painelPrincipal.add(botaoComprarLivro, gbc);

        // Botão "Ver histórico de vendas"
        JButton botaoHistoricoVendas = criarBotao("histórico vendas");
        botaoHistoricoVendas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para a ação do botão "Ver histórico de vendas"
            }
        });
        gbc.gridy = 5;
        painelPrincipal.add(botaoHistoricoVendas, gbc);

        // Botão "Ver histórico de compras"
        JButton botaoHistoricoCompras = criarBotao("histórico compras");
        botaoHistoricoCompras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para a ação do botão "Ver histórico de compras"
            }
        });
        gbc.gridy = 6;
        painelPrincipal.add(botaoHistoricoCompras, gbc);

        // Adiciona o painel principal à janela
        getContentPane().add(painelPrincipal);

        // Exibe a janela
        setVisible(true);
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setPreferredSize(new Dimension(150, 30));
        return botao;
    }

    public static void main(String[] args) {
        TelaOpcoes telaOpcoes = new TelaOpcoes();
    }
}

