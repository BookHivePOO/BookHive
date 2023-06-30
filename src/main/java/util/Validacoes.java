package util;

import java.util.regex.Pattern;

public class Validacoes {

    public static boolean validarCadastroUsuario(String nome, long cpf, String email, String senha) {
        try {
            if (nome.isEmpty()) {
                throw new IllegalArgumentException("Nome não pode ser vazio.");
            }

            String cpfString = String.valueOf(cpf);
            if (cpfString.length() != 11) {
                throw new IllegalArgumentException("CPF inválido. Deve conter 11 dígitos.");
            }

            String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
            Pattern pattern = Pattern.compile(emailRegex);
            if (!pattern.matcher(email).matches()) {
                throw new IllegalArgumentException("Email com formato inválido.");
            }

            if (senha.length() < 6) {
                throw new IllegalArgumentException("Senha deve ter pelo menos 6 caracteres.");
            }

            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

