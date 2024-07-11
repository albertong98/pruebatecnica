package business.orders.command;

import business.util.Command;
import persistence.orders.PedidoGateway;
import persistence.orders.PersistenceFactory;

import java.sql.Connection;
import java.sql.ResultSet;

public class FindAll implements Command<ResultSet> {
    private PedidoGateway gateway = PersistenceFactory.forPedido();
    /*
     * Lista todos los pedidos de base de datos
     *
     * @param Connection c, la conexion obtenida del pool
     * @returns ResultSet rs con los datos obtenidos */
    @Override
    public ResultSet execute(Connection c) {
        return gateway.findAll(c);
    }
}
