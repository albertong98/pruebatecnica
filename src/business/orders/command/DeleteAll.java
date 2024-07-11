package business.orders.command;

import business.util.Command;
import persistence.orders.PedidoGateway;
import persistence.orders.PersistenceFactory;

import java.sql.Connection;

public class DeleteAll implements Command<Void> {
    private PedidoGateway gateway = PersistenceFactory.forPedido();
    @Override
    public Void execute(Connection c) {
        gateway.deleteAll(c);
        return null;
    }
}
