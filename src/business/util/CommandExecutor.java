package business.util;

import config.BasicConnectionPool;
import config.Jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class CommandExecutor {
    BasicConnectionPool pool;
    public CommandExecutor(BasicConnectionPool pool){
        this.pool = pool;
    }

    /*
    * Trata la gestion de conexiones con base de datos y llama al metodo execute del objeto Command
    *
    * @param Command<T> cmd, objeto Command que sera ejecutado
    * @returns el valor obtenido en la llamada a base de datos, si lo hubiera
    * */
    public <T> T execute(Command<T> cmd){
        Connection c = null;
        try {
            c = pool.getConnection();
            c.setAutoCommit(false);

            T res = cmd.execute(c);

            c.commit();

            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            pool.releaseConnection(c);
        }
    }
}
