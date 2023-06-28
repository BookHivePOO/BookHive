package conection;

import java.sql.Connection;

public abstract class AbstractDAO {
    protected Connection connection;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }
}
