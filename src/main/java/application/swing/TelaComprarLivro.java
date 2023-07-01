package application.swing;

import application.swing.TelaOpcoes;
import dao.EnderecoDAO;
import dao.PagamentoDAO;
import dao.LivroDAO;
import dao.TransacaoDAO;
import dto.LivroDTO;
import model.Pagamento;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TelaComprarLivro extends JFrame {

    private JPanel painelLivros;
    private JFrame janelaCadastroLivros;
    private String nome;

    public TelaComprarLivro(long idUsuario, String nome) {
        this.nome = nome;

        janelaCadastroLivros = this;
        // Configurações da janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Comprar Livro");
        setSize(600, 600);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Painel de busca
        JPanel painelBusca = criarPainelBusca(idUsuario);

        // Painel de livros
        painelLivros = new JPanel(new GridLayout(0, 3, 10, 10));
        JScrollPane scrollPane = new JScrollPane(painelLivros);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Obter a lista de livros do banco de dados
        List<LivroDTO> livros = new LivroDAO().listarTodosLivros();

        // Preencher o painel de livros com os componentes
        preencherPainelLivros(livros, idUsuario);

        // Adicionar os painéis de busca e livros ao painel principal
        painelPrincipal.add(painelBusca, BorderLayout.NORTH);
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Botão "Voltar"
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fechar a janela atual
                new TelaOpcoes(idUsuario, nome);
            }
        });

        // Adicionar o painel principal e o botão "Voltar" à janela
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(painelPrincipal, BorderLayout.CENTER);
        getContentPane().add(botaoVoltar, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel criarPainelBusca(long idUsuario) {
        JPanel painelBusca = new JPanel(new FlowLayout()); // Alterado para FlowLayout
        JTextField campoBusca = new JTextField(20);

        JButton botaoPesquisarNome = new JButton("Pesquisar por Nome");
        botaoPesquisarNome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeBusca = campoBusca.getText();
                List<LivroDTO> livrosPorNome = new LivroDAO().pesquisarLivrosPorNome(nomeBusca);
                atualizarPainelLivros(livrosPorNome, idUsuario);
            }
        });

        JButton botaoPesquisarGenero = new JButton("Pesquisar por Gênero");
        botaoPesquisarGenero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String generoBusca = campoBusca.getText();
                List<LivroDTO> livrosPorGenero = new LivroDAO().listarLivrosPorGenero(generoBusca);
                atualizarPainelLivros(livrosPorGenero, idUsuario);
            }
        });

        painelBusca.add(campoBusca);
        painelBusca.add(botaoPesquisarNome);
        painelBusca.add(botaoPesquisarGenero);

        return painelBusca;
    }

    private void preencherPainelLivros(List<LivroDTO> livros, long idUsuario) {
        for (LivroDTO livro : livros) {
            JPanel painelLivro = new JPanel(new BorderLayout());
            painelLivro.setPreferredSize(new Dimension(180, 250));
            painelLivro.setBorder(BorderFactory.createEtchedBorder());

            // Criar o painel com a foto do livro
            JPanel painelFoto = criarPainelFoto(livro.getFoto(), livro.getTitulo());
            painelLivro.add(painelFoto, BorderLayout.CENTER);

            // Adicionar o título do livro abaixo do painel da foto
            JLabel labelTitulo = new JLabel(livro.getTitulo());
            labelTitulo.setHorizontalAlignment(JLabel.CENTER);
            painelLivro.add(labelTitulo, BorderLayout.SOUTH);

            // Adicionar um botão de compra abaixo do título do livro
            JButton botaoDetalhes = new JButton("Detalhes");
            botaoDetalhes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Abrir uma nova janela com as informações detalhadas do livro
                    exibirDetalhesLivro(livro, idUsuario);
                }
            });
            JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
            painelBotoes.add(botaoDetalhes);
            painelLivro.add(painelBotoes, BorderLayout.SOUTH);

            // Adicionar o painel do livro ao painel de livros
            painelLivros.add(painelLivro);
        }
    }

    private void atualizarPainelLivros(List<LivroDTO> livros, long idUsuario) {
        painelLivros.removeAll();
        preencherPainelLivros(livros, idUsuario);
        painelLivros.revalidate();
        painelLivros.repaint();
    }

    private JPanel criarPainelFoto(String url, String tituloLivro) {
        int tamanhoCapa = 150; // Tamanho da capa de livro

        JPanel painelFoto = new JPanel(new BorderLayout());
        painelFoto.setPreferredSize(new Dimension(tamanhoCapa, tamanhoCapa));

        if (url == null || url.isEmpty()) {
            // Criar um painel cinza com uma capa cinza
            painelFoto.setBackground(Color.GRAY);
            JLabel labelCapa = new JLabel("Capa Ausente");
            labelCapa.setHorizontalAlignment(JLabel.CENTER);
            painelFoto.add(labelCapa, BorderLayout.CENTER);
        } else {
            // Carregar a imagem do livro
            ImageIcon imagemLivro = new ImageIcon(url);
            if (imagemLivro.getIconWidth() == -1 || imagemLivro.getIconHeight() == -1) {
                // Se a imagem não puder ser carregada, exibir uma capa cinza
                painelFoto.setBackground(Color.GRAY);
                JLabel labelCapa = new JLabel("Capa Ausente");
                labelCapa.setHorizontalAlignment(JLabel.CENTER);
                painelFoto.add(labelCapa, BorderLayout.CENTER);
            } else {
                // Redimensionar a imagem para caber na capa
                Image imagemRedimensionada = imagemLivro.getImage().getScaledInstance(tamanhoCapa, -1, Image.SCALE_SMOOTH);
                ImageIcon imagemRedimensionadaIcon = new ImageIcon(imagemRedimensionada);

                // Exibir a imagem do livro
                JLabel labelImagem = new JLabel(imagemRedimensionadaIcon);
                painelFoto.add(labelImagem, BorderLayout.CENTER);
            }
        }

        // Adicionar o título do livro abaixo da foto
        JLabel labelTitulo = new JLabel(tituloLivro);
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        painelFoto.add(labelTitulo, BorderLayout.SOUTH);

        return painelFoto;
    }

    private void exibirDetalhesLivro(LivroDTO livro, long idUsuario) {
        // Criar uma nova janela para exibir as informações detalhadas do livro
        JFrame janelaDetalhes = new JFrame("Detalhes do Livro");
        janelaDetalhes.setSize(400, 400);
        janelaDetalhes.setLocationRelativeTo(this);

        // Painel principal
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Painel de informações do livro
        JPanel painelInfoLivro = new JPanel(new GridLayout(5, 2, 10, 10));
        painelInfoLivro.setBorder(BorderFactory.createEtchedBorder());

        // Adicionar as informações do livro ao painel
        painelInfoLivro.add(new JLabel("Título:"));
        painelInfoLivro.add(new JLabel(livro.getTitulo()));
        painelInfoLivro.add(new JLabel("Autor:"));
        painelInfoLivro.add(new JLabel(livro.getAutor()));
        painelInfoLivro.add(new JLabel("Descrição:"));
        painelInfoLivro.add(new JLabel(livro.getDescricao()));
        painelInfoLivro.add(new JLabel("Número de Páginas:"));
        painelInfoLivro.add(new JLabel(String.valueOf(livro.getNumeroPaginas())));
        painelInfoLivro.add(new JLabel("Gênero:"));
        painelInfoLivro.add(new JLabel(livro.getGenero()));

        // Adicionar o painel de informações do livro ao painel principal
        painelPrincipal.add(painelInfoLivro, BorderLayout.CENTER);

        // Botão de redirecionamento para a tela de endereço e pagamento
        JButton botaoIrParaPagamento = new JButton("Ir para Endereço e Pagamento");
        botaoIrParaPagamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaDetalhes.dispose(); // Fechar a janela atual
                exibirTelaEndereco(idUsuario, livro); // Ir para a tela de endereço e pagamento
            }
        });
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotoes.add(botaoIrParaPagamento);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        // Adicionar o painel principal à janela
        janelaDetalhes.getContentPane().add(painelPrincipal);

        // Exibir a janela de detalhes do livro
        janelaDetalhes.setVisible(true);
    }

    private void exibirTelaEndereco(long idUsuario, LivroDTO livro) {
        // Criar uma nova janela para exibir as informações de endereço
        JFrame janelaEndereco = new JFrame("Informações de Endereço");
        janelaEndereco.setSize(400, 400);
        janelaEndereco.setLocationRelativeTo(null);

        // Painel principal
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Painel de campos de entrada
        JPanel painelCampos = new JPanel(new GridLayout(7, 2, 10, 10));
        painelCampos.setBorder(BorderFactory.createEtchedBorder());

        // Campos de entrada para as informações de endereço
        JTextField campoLogradouro = new JTextField(20);
        JTextField campoNumero = new JTextField(10);
        JTextField campoComplemento = new JTextField(20);
        JTextField campoBairro = new JTextField(20);
        JTextField campoCidade = new JTextField(20);
        JTextField campoEstado = new JTextField(10);
        JTextField campoCep = new JTextField(10);

        // Layout para os campos de entrada
        painelCampos.add(new JLabel("Logradouro:"));
        painelCampos.add(campoLogradouro);
        painelCampos.add(new JLabel("Número:"));
        painelCampos.add(campoNumero);
        painelCampos.add(new JLabel("Complemento:"));
        painelCampos.add(campoComplemento);
        painelCampos.add(new JLabel("Bairro:"));
        painelCampos.add(campoBairro);
        painelCampos.add(new JLabel("Cidade:"));
        painelCampos.add(campoCidade);
        painelCampos.add(new JLabel("Estado:"));
        painelCampos.add(campoEstado);
        painelCampos.add(new JLabel("CEP:"));
        painelCampos.add(campoCep);

        // Botão para enviar as informações de endereço
        JButton botaoEnviarEndereco = new JButton("Enviar");
        botaoEnviarEndereco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obter os valores dos campos de entrada
                String logradouro = campoLogradouro.getText();
                int numero = Integer.parseInt(campoNumero.getText());
                String complemento = campoComplemento.getText();
                String bairro = campoBairro.getText();
                String cidade = campoCidade.getText();
                String estado = campoEstado.getText();
                long cep = Long.parseLong(campoCep.getText());

                // Cadastrar o endereço no banco de dados
                int idEndereco = new EnderecoDAO().cadastrarEndereco(logradouro, numero, complemento, bairro, cidade, estado, cep, (int) idUsuario);

                // Verificar se o cadastro do endereço foi bem-sucedido
                if (idEndereco != -1) {
                    janelaEndereco.dispose(); // Fechar a janela atual
                    exibirTelaPagamento(idUsuario, livro, idEndereco); // Ir para a tela de pagamento
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar endereço. Por favor, verifique os dados e tente novamente.");
                }
            }
        });

        // Adicionar os campos de entrada e o botão ao painel principal
        painelPrincipal.add(painelCampos, BorderLayout.CENTER);
        painelPrincipal.add(botaoEnviarEndereco, BorderLayout.SOUTH);

        // Adicionar o painel principal à janela
        janelaEndereco.getContentPane().add(painelPrincipal);

        // Exibir a janela de informações de endereço
        janelaEndereco.setVisible(true);
    }

    private void exibirTelaPagamento(long idUsuario, LivroDTO livro, int idEndereco) {
        // Criar uma nova janela para exibir as informações de pagamento
        JFrame janelaPagamento = new JFrame("Informações de Pagamento");
        janelaPagamento.setSize(400, 400);
        janelaPagamento.setLocationRelativeTo(null);

        // Painel principal
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Painel de campos de entrada
        JPanel painelCampos = new JPanel(new GridLayout(5, 2, 10, 10));
        painelCampos.setBorder(BorderFactory.createEtchedBorder());

        // Campos de entrada para as informações de pagamento
        JTextField campoNomeCartao = new JTextField(20);
        JTextField campoNumeroCartao = new JTextField(20);
        JTextField campoBandeira = new JTextField(20);
        JTextField campoDataValidade = new JTextField(10);
        JTextField campoCodigoSeguranca = new JTextField(10);

        // Layout para os campos de entrada
        painelCampos.add(new JLabel("Nome do Cartão:"));
        painelCampos.add(campoNomeCartao);
        painelCampos.add(new JLabel("Número do Cartão:"));
        painelCampos.add(campoNumeroCartao);
        painelCampos.add(new JLabel("Bandeira:"));
        painelCampos.add(campoBandeira);
        painelCampos.add(new JLabel("Data de Validade (MM/AAAA):"));
        painelCampos.add(campoDataValidade);
        painelCampos.add(new JLabel("Código de Segurança:"));
        painelCampos.add(campoCodigoSeguranca);

        // Botão para enviar as informações de pagamento
        JButton botaoEnviarPagamento = new JButton("Enviar");
        botaoEnviarPagamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obter os valores dos campos de entrada
                String nomeCartao = campoNomeCartao.getText();
                String numeroCartao = campoNumeroCartao.getText();
                String bandeira = campoBandeira.getText();
                String dataValidadeStr = campoDataValidade.getText();
                long codigoSeguranca = Long.parseLong(campoCodigoSeguranca.getText());

                // Converter a data de validade para o formato correto
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
                Date dataValidade;
                try {
                    dataValidade = dateFormat.parse(dataValidadeStr);

                    Pagamento pagamento = new PagamentoDAO().cadastrarPagamento(nomeCartao, numeroCartao, bandeira, dataValidade, codigoSeguranca);

                    // Efetuar a compra
                    boolean compraEfetuada = new TransacaoDAO().efetuarCompra(livro.getId(), idEndereco, (int) idUsuario, pagamento.getIdPagamento());

                    if (compraEfetuada) {
                        janelaPagamento.dispose(); // Fechar a janela atual
                        exibirTelaPagamentoConcluido(idUsuario); // Exibir a tela de pagamento concluído
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao efetuar a compra. Por favor, verifique as informações e tente novamente.");
                    }
                } catch (java.text.ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Data de validade inválida. Certifique-se de que esteja no formato MM/AAAA.");
                }
            }
        });

        // Adicionar os campos de entrada e o botão ao painel principal
        painelPrincipal.add(painelCampos, BorderLayout.CENTER);
        painelPrincipal.add(botaoEnviarPagamento, BorderLayout.SOUTH);

        // Adicionar o painel principal à janela
        janelaPagamento.getContentPane().add(painelPrincipal);

        // Exibir a janela de informações de pagamento
        janelaPagamento.setVisible(true);
    }

    private void exibirTelaPagamentoConcluido(long idUsuario) {
        // Criar uma nova janela para exibir a confirmação de pagamento
        JFrame janelaConcluido = new JFrame("Pagamento Concluído");
        janelaConcluido.setSize(400, 200);
        janelaConcluido.setLocationRelativeTo(null);

        // Painel principal
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Painel de mensagem de confirmação
        JPanel painelMensagem = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel labelMensagem = new JLabel("Pagamento concluído com sucesso!");
        painelMensagem.add(labelMensagem);

        // Botão OK para fechar a janela
        JButton botaoOk = new JButton("OK");
        botaoOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaConcluido.dispose(); // Fechar a janela atual
                janelaCadastroLivros.dispose();
                new TelaComprarLivro(idUsuario, nome); // Voltar para a tela de livros cadastrados
            }
        });

        // Adicionar o painel de mensagem e o botão OK ao painel principal
        painelPrincipal.add(painelMensagem, BorderLayout.CENTER);
        painelPrincipal.add(botaoOk, BorderLayout.SOUTH);

        // Adicionar o painel principal à janela
        janelaConcluido.getContentPane().add(painelPrincipal);

        // Exibir a janela de pagamento concluído
        janelaConcluido.setVisible(true);
    }
}
