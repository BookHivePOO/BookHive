package application.swing;

import dao.GeneroDAO;
import dao.LivroDAO;
import dto.LivroDTO;
import model.Livro;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

public class TelaCadastroLivro extends JFrame {

    private JTextField campoTitulo;
    private JTextField campoAutor;
    private JTextField campoDescricao;
    private JTextField campoNumeroPaginas;
    private JTextField campoGenero;
    private JTextField campoPreco;
    private JTextField campoFoto;
    private JTextField campoIdUsuarioVenda;
    private JTextField campoEstado;

    private String nome;

    public TelaCadastroLivro( long idUsuario, String nome) {
        this.nome = nome;
        // Configurações da janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Livro");
        setSize(600, 600);
        setLocationRelativeTo(null);

        // Painel principal com layout GridBagLayout
        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 20, 10, 20);

        // Campos de texto: título, autor, descrição, número de páginas, gênero, preço, foto, ID do usuário de venda, estado
        campoTitulo = new JTextField(30);
        campoAutor = new JTextField(30);
        campoDescricao = new JTextField(30);
        campoNumeroPaginas = new JTextField(30);
        campoGenero = new JTextField(30);
        campoPreco = new JTextField(30);
        campoFoto = new JTextField(30);
        campoIdUsuarioVenda = new JTextField(30);
        campoEstado = new JTextField(30);

        // Botão "Cadastrar"
        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(e -> cadastrarLivro(idUsuario));

        // Adicione os componentes ao painel principal
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelPrincipal.add(new JLabel("Título:"), gbc);
        gbc.gridy = 1;
        painelPrincipal.add(new JLabel("Autor:"), gbc);
        gbc.gridy = 2;
        painelPrincipal.add(new JLabel("Descrição:"), gbc);
        gbc.gridy = 3;
        painelPrincipal.add(new JLabel("Número de Páginas:"), gbc);
        gbc.gridy = 4;
        painelPrincipal.add(new JLabel("Gênero:"), gbc);
        gbc.gridy = 5;
        painelPrincipal.add(new JLabel("Preço:"), gbc);
        gbc.gridy = 6;
        painelPrincipal.add(new JLabel("Foto:"), gbc);
        gbc.gridy = 7;
        painelPrincipal.add(new JLabel("Estado:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        painelPrincipal.add(campoTitulo, gbc);
        gbc.gridy = 1;
        painelPrincipal.add(campoAutor, gbc);
        gbc.gridy = 2;
        painelPrincipal.add(campoDescricao, gbc);
        gbc.gridy = 3;
        painelPrincipal.add(campoNumeroPaginas, gbc);
        gbc.gridy = 4;
        painelPrincipal.add(campoGenero, gbc);
        gbc.gridy = 5;
        painelPrincipal.add(campoPreco, gbc);
        gbc.gridy = 6;
        painelPrincipal.add(campoFoto, gbc);
        gbc.gridy = 7;
        painelPrincipal.add(campoEstado, gbc);
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        painelPrincipal.add(botaoCadastrar, gbc);

        // Adicione o painel principal à janela
        getContentPane().add(painelPrincipal, BorderLayout.CENTER);

        // Exiba a janela
        setVisible(true);
    }

    private void cadastrarLivro(long idUsuario) {
        String titulo = campoTitulo.getText();
        String autor = campoAutor.getText();
        String descricao = campoDescricao.getText();
        int numeroPaginas = Integer.parseInt(campoNumeroPaginas.getText());
        String genero = campoGenero.getText();
        double preco = Double.parseDouble(campoPreco.getText());
        String foto = campoFoto.getText();
        String estado = campoEstado.getText();

        LivroDAO livroDAO = new LivroDAO();
        boolean cadastroSucesso = livroDAO.cadastrarLivro(titulo, autor, descricao, numeroPaginas, genero, preco, foto, idUsuario, estado);

        if (cadastroSucesso) {
            // Cadastro bem-sucedido
            JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso.");

            List< LivroDTO> livro = new LivroDAO().listarTodosLivros();

            // Redirecionar para a tela de opções
            TelaOpcoes telaOpcoes = new TelaOpcoes(idUsuario, nome);
            telaOpcoes.setVisible(true);
            dispose(); // Fechar a janela de cadastro de livro
        } else {
            // Cadastro falhou
            // Exibir mensagem de erro, se necessário
            JOptionPane.showMessageDialog(this, "Falha ao cadastrar livro. Verifique os dados e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
