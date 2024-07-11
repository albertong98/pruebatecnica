package business.util;

import business.orders.PedidoDTO;
import business.orders.command.AddPedido;
import business.orders.command.DeleteAll;
import config.BasicConnectionPool;

import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class Reader {
    ConcurrentMap<String,Map<String,Integer>> resumen = new ConcurrentHashMap<>();
    private CommandExecutor executor;

    public Reader(){
        resumen.put("Region",new ConcurrentHashMap<>());
        resumen.put("Country",new ConcurrentHashMap<>());
        resumen.put("Item type",new ConcurrentHashMap<>());
        resumen.put("Sales channel",new ConcurrentHashMap<>());
        resumen.put("Order priority",new ConcurrentHashMap<>());
    }

    /*
    * Llama al método encargado de recuperar los pedidos implementado por las subclases
    * borra la tabla de pedidos
    *
    * @returns el objeto Map con el resumen de los datos encontrados
    * */
    public Map<String,Map<String,Integer>> readPedidos(){
        try {
            BasicConnectionPool pool = BasicConnectionPool.create();
            executor = new CommandExecutor(pool);
            executor.execute(new DeleteAll());
            this.parsePedidos();
            pool.shutdown();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resumen;
    }

    /*
    * Método abstracto que recupera los datos de pedidos, cada subclase usa una fuente distinta
    * */
    protected abstract void parsePedidos();

    /*
     * Añade un pedido a base de datos usando un objeto Dto como base y añade sus datos al resumen
     *
     * @param PedidoDTO dto, DTO que contiene toda la información del pedido
     * */
    protected void addPedido(PedidoDTO dto){
        resumen.get("Region").merge(dto.getRegion(), 1, Integer::sum);
        resumen.get("Country").merge(dto.getCountry(), 1, Integer::sum);
        resumen.get("Item type").merge(dto.getItemType(), 1, Integer::sum);
        resumen.get("Sales channel").merge(dto.getSalesChannel(), 1, Integer::sum);
        resumen.get("Order priority").merge(dto.getOrderPriority(), 1, Integer::sum);

        executor.execute(new AddPedido(dto));
    }

}
