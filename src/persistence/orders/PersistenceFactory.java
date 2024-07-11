package persistence.orders;

import persistence.orders.impl.PedidoGatewayImpl;

public class PersistenceFactory {
     public static PedidoGateway forPedido(){
        return new PedidoGatewayImpl();
    }
}
