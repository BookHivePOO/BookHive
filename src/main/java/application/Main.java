package application;

import dao.CredencialDAO;
import dao.EnderecoDAO;
import dao.LivroDAO;
import dao.PagamentoDAO;
import dao.TransacaoDAO;
import dao.UsuarioDAO;
import dto.LivroDTO;
import dto.TransacaoDTO;
import model.Pagamento;
import model.Transacao;
import model.Usuario;
import util.RelatorioGenerator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Criação do objeto UsuarioDAO e LivroDAO
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        CredencialDAO credencialDAO = new CredencialDAO();
        LivroDAO livroDAO = new LivroDAO();
        TransacaoDAO transacaoDAO = new TransacaoDAO();

        // Criação do objeto Scanner para leitura de entrada do usuário
        Scanner scanner = new Scanner(System.in);

        int opcao;
        Usuario usuarioLogado = null;

        do {
            System.out.println("Escolha uma opção:");
            System.out.println("0 - Gerar relatorios");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastrar");


            if (usuarioLogado != null) {
                System.out.println("3 - Cadastrar Livro");
                System.out.println("4 - Pesquisar Livro por Nome");
                System.out.println("5 - Listar Livros por Gênero");
                System.out.println("6 - Listar todos os livros");
                System.out.println("7 - Comprar livro");
                System.out.println("8 - Ver histórico de vendas");
                System.out.println("9 - Ver histórico de compras");
            }
            System.out.println("100 - Fechar");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer de leitura

            switch (opcao) {
                case 1:
                    // Solicitar dados para o login
                    System.out.println("Digite o email para login:");
                    String email = scanner.nextLine();

                    System.out.println("Digite a senha para login:");
                    String senha = scanner.nextLine();

                    // Realizar o login
                    Usuario loginSucesso = credencialDAO.realizarLogin(email, senha);
                    if (loginSucesso != null) {
                        usuarioLogado = loginSucesso;
                        System.out.println("Login realizado com sucesso!");
                    } else {
                        System.out.println("Deseja tentar novamente? (S/N)");
                        String resposta = scanner.nextLine();
                        if (!resposta.equalsIgnoreCase("S")) {
                            opcao = 0; // Fechar o programa
                        }
                    }
                    break;
                case 2:
                    // Solicitar dados para o cadastro de usuário
                    System.out.println("Digite o nome:");
                    String nome = scanner.nextLine();

                    System.out.println("Digite o CPF:");
                    long cpf = scanner.nextLong();
                    scanner.nextLine(); // Limpar o buffer de leitura

                    System.out.println("Digite o email:");
                    email = scanner.nextLine();

                    System.out.println("Digite a senha:");
                    senha = scanner.nextLine();

                    // Cadastro do usuário
                    usuarioDAO.cadastrarUsuario(nome, cpf, email, senha);

                    break;
                case 0:
                    RelatorioGenerator.gerarRelatorioCompleto();
                    break;
                case 3:
                    if (usuarioLogado != null) {
                        // Solicitar dados para o cadastro do livro
                        System.out.println("Digite o título do livro:");
                        String titulo = scanner.nextLine();

                        System.out.println("Digite o autor do livro:");
                        String autor = scanner.nextLine();

                        System.out.println("Digite a descrição do livro:");
                        String descricao = scanner.nextLine();

                        System.out.println("Digite o número de páginas do livro:");
                        int numeroPaginas = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer de leitura

                        System.out.println("Digite o gênero do livro:");
                        String genero = scanner.nextLine();

                        System.out.println("Digite o preço do livro:");
                        double preco = scanner.nextDouble();
                        scanner.nextLine(); // Limpar o buffer de leitura

                        System.out.println("Digite a foto do livro:");
                        String foto = scanner.nextLine();

                        System.out.println("Digite o estado do livro:");
                        String estado = scanner.nextLine();

                        // Cadastro do livro
                        livroDAO.cadastrarLivro(titulo, autor, descricao, numeroPaginas, genero, preco, foto, usuarioLogado.getId(), estado);
                    } else {
                        System.out.println("É necessário fazer login para cadastrar um livro.");
                    }

                    break;
                case 4:
                    if (usuarioLogado != null) {
                        // Solicitar o nome do livro a ser pesquisado
                        System.out.println("Digite o nome do livro:");
                        String nomeLivro = scanner.nextLine();

                        // Pesquisar livros por nome
                        List<LivroDTO> livrosPorNome = livroDAO.pesquisarLivrosPorNome(nomeLivro);

                        if (livrosPorNome.isEmpty()) {
                            System.out.println("Nenhum livro encontrado com esse nome.");
                        } else {
                            System.out.println("Livros encontrados:");
                            for (LivroDTO livro : livrosPorNome) {
                                System.out.println(" ");
                                System.out.println("---------------------------------------------------");
                                livro.imprimirLivro();
                            }
                            System.out.println("---------------------------------------------------");
                        }
                    } else {
                        System.out.println("É necessário fazer login para pesquisar um livro por nome.");
                    }

                    break;
                case 5:
                    if (usuarioLogado != null) {
                        // Solicitar o gênero dos livros a serem listados
                        System.out.println("Digite o gênero dos livros:");
                        String genero = scanner.nextLine();

                        // Listar livros por gênero
                        List<LivroDTO> livrosPorGenero = livroDAO.listarLivrosPorGenero(genero);

                        if (livrosPorGenero.isEmpty()) {
                            System.out.println("Nenhum livro encontrado nesse gênero.");
                        } else {
                            System.out.println("Livros encontrados:");
                            for (LivroDTO livro : livrosPorGenero) {
                                System.out.println(" ");
                                System.out.println("---------------------------------------------------");
                                livro.imprimirLivro();
                            }
                            System.out.println("---------------------------------------------------");
                        }
                    } else {
                        System.out.println("É necessário fazer login para listar os livros por gênero.");
                    }

                    break;
                case 6:
                    if (usuarioLogado != null) {

                        // Listar livros por gênero
                        List<LivroDTO> todosLivros = livroDAO.listarTodosLivros();

                        if (todosLivros.isEmpty()) {
                            System.out.println("Nenhum livro encontrado.");
                        } else {
                            System.out.println("Livros encontrados:");
                            for (LivroDTO livro : todosLivros) {
                                System.out.println(" ");
                                System.out.println("---------------------------------------------------");
                                livro.imprimirLivro();
                            }
                            System.out.println("---------------------------------------------------");
                        }
                    } else {
                        System.out.println("É necessário fazer login para listar os livros por gênero.");
                    }
                    break;
                case 7:
                    if (usuarioLogado != null) {
                        // Solicitar o ID do livro a ser comprado
                        System.out.println("Digite o ID do livro que deseja comprar:");
                        int idLivroCompra = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer de leitura

                        // Verificar se o livro existe
                        LivroDTO livroCompra = livroDAO.pesquisarLivroPorId(idLivroCompra);
                        if (livroCompra != null) {
                            // Exibir informações do livro
                            System.out.println("Livro selecionado:");
                            System.out.println("ID: " + livroCompra.getId());
                            System.out.println("Título: " + livroCompra.getTitulo());
                            System.out.println("Autor: " + livroCompra.getAutor());

                            // Solicitar informações de pagamento
                            System.out.println("Digite o nome do cartão:");
                            String nomeCartao = scanner.nextLine();

                            System.out.println("Digite o número do cartão:");
                            String numeroCartao = scanner.nextLine();

                            System.out.println("Digite a bandeira do cartão:");
                            String bandeira = scanner.nextLine();

                            System.out.println("Digite a data de validade do cartão (formato: yyyy-MM-dd):");
                            String dataValidadeStr = scanner.nextLine();
                            Date dataValidade = null;
                            try {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                dataValidade = dateFormat.parse(dataValidadeStr);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            System.out.println("Digite o código de segurança do cartão:");
                            long codigoSeguranca = scanner.nextLong();
                            scanner.nextLine(); // Limpar o buffer de leitura

                            System.out.println("Digite o logradouro de entrega:");
                            String logradouroEntrega = scanner.nextLine();

                            System.out.println("Digite o número de entrega:");
                            int numeroEntrega = scanner.nextInt();
                            scanner.nextLine(); // Limpar o buffer de leitura

                            System.out.println("Digite o complemento de entrega:");
                            String complementoEntrega = scanner.nextLine();

                            System.out.println("Digite o bairro de entrega:");
                            String bairroEntrega = scanner.nextLine();

                            System.out.println("Digite a cidade de entrega:");
                            String cidadeEntrega = scanner.nextLine();

                            System.out.println("Digite o estado de entrega:");
                            String estadoEntrega = scanner.nextLine();

                            System.out.println("Digite o CEP de entrega:");
                            long cepEntrega = scanner.nextLong();
                            scanner.nextLine(); // Limpar o buffer de leitura

                            // Cadastro do endereço de entrega
                            int idEnderecoEntrega = new EnderecoDAO().cadastrarEndereco(logradouroEntrega, numeroEntrega, complementoEntrega, bairroEntrega, cidadeEntrega, estadoEntrega, cepEntrega, (int) usuarioLogado.getId());


                            // Criação do objeto Pagamento com os dados informados pelo usuário
                            PagamentoDAO pagamentoDAO = new PagamentoDAO();
                            Pagamento pagamento = pagamentoDAO.cadastrarPagamento(nomeCartao, numeroCartao, bandeira, dataValidade, codigoSeguranca);

                            // Realizar a transação de compra
                            boolean transacaoEfetuada = transacaoDAO.efetuarCompra(idLivroCompra, idEnderecoEntrega, (int) usuarioLogado.getId(), pagamento.getIdPagamento());

                            if (transacaoEfetuada) {
                                System.out.println("Compra realizada com sucesso!");
                            } else {
                                System.out.println("Falha ao efetuar a compra.");
                            }
                        } else {
                            System.out.println("Livro não encontrado com o ID informado.");
                        }
                    } else {
                        System.out.println("É necessário fazer login para comprar um livro.");
                    }
                    break;
                case 8:
                    if (usuarioLogado != null) {
                        // Verificar se o usuário é um vendedor
                        // Obter o ID do vendedor logado
                        int idVendedor = (int) usuarioLogado.getId();

                        // Obter o histórico de vendas do vendedor
                        List<TransacaoDTO> historicoVendas = transacaoDAO.pesquisarVendasPorIdUsuario(idVendedor);

                        if (historicoVendas.isEmpty()) {
                            System.out.println("Nenhuma venda realizada.");
                        } else {
                            System.out.println("Histórico de vendas:");
                            for (TransacaoDTO venda : historicoVendas) {
                                System.out.println(" ");
                                System.out.println("---------------------------------------------------");
                                venda.imprimirTransacao();
                            }
                            System.out.println("---------------------------------------------------");
                        }
                    } else {
                        System.out.println("É necessário fazer login para ver o histórico de vendas.");
                    }
                    break;
                case 9:
                    if (usuarioLogado != null) {
                        // Verificar se o usuário é um vendedor
                        if (usuarioLogado != null) {
                            // Obter o ID do vendedor logado
                            int idVendedor = (int) usuarioLogado.getId();

                            // Obter o histórico de compras do vendedor
                            List<TransacaoDTO> historicoCompras = transacaoDAO.pesquisarComprasPorIdUsuario(idVendedor);

                            if (historicoCompras.isEmpty()) {
                                System.out.println("Nenhuma compra realizada.");
                            } else {
                                System.out.println("Histórico de compras:");
                                for (TransacaoDTO compra : historicoCompras) {
                                    System.out.println(" ");
                                    System.out.println("---------------------------------------------------");
                                    compra.imprimirTransacao();
                                }
                                System.out.println("---------------------------------------------------");
                            }
                        } else {
                            System.out.println("Apenas vendedores têm histórico de compras.");
                        }
                    } else {
                        System.out.println("É necessário fazer login para ver o histórico de compras.");
                    }
                    break;
                case 100:
                    System.out.println("Fechando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        } while (opcao != 0);

        // Fechar o scanner
        scanner.close();
    }
}
