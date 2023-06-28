package application;

import dao.UsuarioDAO;
import dao.LivroDAO;
import model.Usuario;
import model.Livro;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Criação do objeto UsuarioDAO e LivroDAO
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        LivroDAO livroDAO = new LivroDAO();

        // Criação do objeto Scanner para leitura de entrada do usuário
        Scanner scanner = new Scanner(System.in);

        int opcao;
        Usuario usuarioLogado = null;

        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastrar");
            System.out.println("0 - Fechar");

            if (usuarioLogado != null) {
                System.out.println("3 - Cadastrar Livro");
                System.out.println("4 - Pesquisar Livro por Nome");
                System.out.println("5 - Listar Livros por Gênero");
            }

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
                    Usuario loginSucesso = usuarioDAO.realizarLogin(email, senha);
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
                        List<Livro> livrosPorNome = livroDAO.pesquisarLivrosPorNome(nomeLivro);

                        if (livrosPorNome.isEmpty()) {
                            System.out.println("Nenhum livro encontrado com esse nome.");
                        } else {
                            System.out.println("Livros encontrados:");
                            for (Livro livro : livrosPorNome) {
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
                        List<Livro> livrosPorGenero = livroDAO.listarLivrosPorGenero(genero);

                        if (livrosPorGenero.isEmpty()) {
                            System.out.println("Nenhum livro encontrado nesse gênero.");
                        } else {
                            System.out.println("Livros encontrados:");
                            for (Livro livro : livrosPorGenero) {
                                System.out.println(livro);
                            }
                        }
                    } else {
                        System.out.println("É necessário fazer login para listar os livros por gênero.");
                    }

                    break;
                case 0:
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
