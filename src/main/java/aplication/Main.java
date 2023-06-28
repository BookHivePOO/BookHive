package aplication;

import dao.UsuarioDAO;
import model.Credencial;
import model.Usuario;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Criação do objeto UsuarioDAO
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // Criação do objeto Scanner para leitura de entrada do usuário
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastrar");
            System.out.println("0 - Fechar");
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
                    boolean loginSucesso = usuarioDAO.realizarLogin(email, senha);
                    if (!loginSucesso) {
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
