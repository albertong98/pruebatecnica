package persistence.orders;

import business.orders.PedidoDTO;

import java.sql.Connection;
import java.sql.ResultSet;


public interface PedidoGateway {
    ResultSet findAll(Connection c);
    void add(PedidoDTO pedido,Connection c);
    void deleteAll(Connection c);
}
