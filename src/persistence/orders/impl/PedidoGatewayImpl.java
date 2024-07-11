package persistence.orders.impl;

import business.orders.PedidoDTO;
import config.Jdbc;
import config.Queries;
import persistence.orders.PedidoGateway;

import java.sql.*;

public class PedidoGatewayImpl implements PedidoGateway {
    private Queries queries = Queries.getInstance();
    /*
     *
     * busca todos los pedidos en BBDD, ordenados por orderID
     *
     * @param Connection c, la conexion a BBDD
     * @returns ResultSet, el resultSet con todos los registros encontrados
     * */
    @Override
    public ResultSet findAll(Connection c) {
        PreparedStatement pst = null;
        ResultSet rs;

        try {
            pst = c.prepareStatement(queries.getQuery("FIND_ALL_PEDIDOS"));

            rs = pst.executeQuery();

            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Jdbc.close(pst);
        }
    }

    /*
     * añade un pedido a BBDD
     *
     * @param Connection c, la conexion a BBDD
     * */
    @Override
    public void add(PedidoDTO pedido,Connection c) {
        PreparedStatement pst = null;

        try {
            pst = c.prepareStatement(queries.getQuery("ADD_PEDIDO"));

            pst.setString(1, pedido.getOrderID());
            pst.setString(2,pedido.getUUID());
            pst.setString(3, pedido.getOrderPriority());
            pst.setDate(4,Date.valueOf(pedido.getOrderDate()));
            pst.setString(5,pedido.getRegion());
            pst.setString(6,pedido.getCountry());
            pst.setString(7,pedido.getItemType());
            pst.setString(8, pedido.getSalesChannel());
            pst.setDate(9,Date.valueOf(pedido.getShipDate()));
            pst.setInt(10,pedido.getUnitsSold());
            pst.setDouble(11,pedido.getUnitPrice());
            pst.setDouble(12,pedido.getUnitCost());
            pst.setDouble(13,pedido.getTotalRevenue());
            pst.setDouble(14,pedido.getTotalCost());
            pst.setDouble(15,pedido.getTotalProfit());
            pst.setString(16,pedido.getSelfLink());

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Jdbc.close(pst);
        }
    }

    /*
     * borra la tabla de pedidos
     *
     * @param Connection c, la conexion a BBDD
     * */
    @Override
    public void deleteAll(Connection c) {
        PreparedStatement pst = null;
        try {
            pst = c.prepareStatement(queries.getQuery("DELETE_ALL"));

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Jdbc.close(pst);
        }
    }
}
