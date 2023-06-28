package conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por fornecer uma conexão com o banco de dados MySQL.
 */
public class SQLConnection {
    private static final String URL = "jdbc:mysql://localhost/bookhive";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "106652";

    /**
     * Obtém uma conexão com o banco de dados.
     *
     * @return uma instância de Connection que representa a conexão com o banco de dados
     * @throws SQLException se ocorrer um erro durante a conexão com o banco de dados
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
