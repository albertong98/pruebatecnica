package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class BasicConnectionPool{
    private static Conf conf = Conf.getInstance();

    private static final String URL = conf.getProperty("db.url");
    private static final String USER = conf.getProperty("db.user");
    private static final String PASS = conf.getProperty("db.password");
    private Stack<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static int INITIAL_POOL_SIZE = 10;
    private static int MAX_POOL_SIZE = 100;
    private static int TIMEOUT = 15;

    public BasicConnectionPool(Stack<Connection> pool) {
        this.connectionPool = pool;
    }

    public static BasicConnectionPool create() throws SQLException {
        Stack<Connection> pool = new Stack<>();
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) pool.add(createConnection());
        return new BasicConnectionPool(pool);
    }

    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public Connection getConnection() throws SQLException {
        if (connectionPool.isEmpty()) {
            if (usedConnections.size() < MAX_POOL_SIZE) {
                connectionPool.add(createConnection());
            } else {
                throw new RuntimeException("Maximo numero de conexiones alcanzado");
            }
        }

        Connection connection = connectionPool.pop();

        if(!connection.isValid(TIMEOUT)) connection = createConnection();

        usedConnections.add(connection);

        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    public void shutdown() throws SQLException {
        for (Iterator<Connection> it = usedConnections.iterator(); it.hasNext(); ) {
            Connection c = it.next();
            if(c != null) c.close();
            it.remove();
        }
        for (Connection c : connectionPool) {
            if(c != null) c.close();
        }
        connectionPool.clear();
    }
}
