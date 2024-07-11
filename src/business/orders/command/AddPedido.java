package business.orders.command;

import business.util.Command;
import business.orders.PedidoDTO;
import persistence.orders.PedidoGateway;
import persistence.orders.PersistenceFactory;

import java.sql.Connection;

public class AddPedido implements Command<Void> {
    private PedidoDTO dto;
    private PedidoGateway gateway = PersistenceFactory.forPedido();

    public AddPedido(PedidoDTO dto){
        this.dto = dto;
    }
    /*
    * Añade un pedido a base de datos
    *
    * @param Connection c, la conexion obtenida del pool */
    @Override
    public Void execute(Connection c){
        gateway.add(dto,c);
        return null;
    }
}
