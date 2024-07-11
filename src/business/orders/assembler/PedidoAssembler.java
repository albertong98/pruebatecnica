package business.orders.assembler;

import business.orders.PedidoDTO;


import javax.json.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class PedidoAssembler {
    /* Construye un objeto DTO en base a un ResultSet
    *
    * @param ResultSet rs, el ResultSet obtenido de base de datos
    * @returns PedidoDTO, el objeto DTO construido
    * */
    public static PedidoDTO fromResultSet(ResultSet rs) throws SQLException {
        PedidoDTO dto = new PedidoDTO();

        dto.setOrderID(rs.getString("order_id"));
        dto.setOrderPriority(rs.getString("order_priority"));
        dto.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime().toLocalDate());
        dto.setRegion(rs.getString("region"));
        dto.setCountry(rs.getString("country"));
        dto.setItemType(rs.getString("item_type"));
        dto.setSalesChannel(rs.getString("sales_channel"));
        dto.setShipDate(rs.getTimestamp("ship_date").toLocalDateTime().toLocalDate());
        dto.setUnitsSold(rs.getInt("units_sold"));
        dto.setUnitPrice(rs.getDouble("unit_price"));
        dto.setUnitCost(rs.getDouble("unit_cost"));
        dto.setTotalRevenue(rs.getDouble("total_revenue"));
        dto.setTotalCost(rs.getDouble("total_cost"));
        dto.setTotalProfit(rs.getDouble("total_profit"));

        return dto;
    }

    /* Construye un objeto DTO en base a un array de objetos de tipo String
     *
     * @param String[] values, el array con los valores a utilizar
     * @returns PedidoDTO, el objeto DTO construido
     * */
    public static PedidoDTO fromCSV(String[] values) {
        PedidoDTO dto = new PedidoDTO();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/uuuu");
        dto.setUUID(UUID.randomUUID().toString());
        dto.setRegion(values[0]);
        dto.setCountry(values[1]);
        dto.setItemType(values[2]);
        dto.setSalesChannel(values[3]);
        dto.setOrderPriority(values[4]);
        dto.setOrderDate(LocalDate.parse(values[5],formatter));
        dto.setOrderID(values[6]);
        dto.setShipDate(LocalDate.parse(values[7],formatter));
        dto.setUnitsSold(Integer.parseInt(values[8]));
        dto.setUnitPrice(Double.parseDouble(values[9]));
        dto.setUnitCost(Double.parseDouble(values[10]));
        dto.setTotalRevenue(Double.parseDouble(values[11]));
        dto.setTotalCost(Double.parseDouble(values[12]));
        dto.setTotalProfit(Double.parseDouble(values[13]));

        return dto;
    }

    /* Construye un objeto DTO en base a un objeto JSON
     *
     * @param JsonObject jsonObject el JSON recuperado con los datos
     * @returns PedidoDTO, el objeto DTO construido
     * */
    public static PedidoDTO fromJsonValue(JsonObject jsonObject) {
        PedidoDTO dto = new PedidoDTO();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/uuuu");

        dto.setOrderID(jsonObject.getString("id"));
        dto.setUUID(jsonObject.getString("uuid"));
        dto.setOrderPriority(jsonObject.getString("priority"));
        dto.setOrderDate(LocalDate.parse(jsonObject.getString("date"),formatter));
        dto.setRegion(jsonObject.getString("region"));
        dto.setCountry(jsonObject.getString("country"));
        dto.setItemType(jsonObject.getString("item_type"));
        dto.setSalesChannel(jsonObject.getString("sales_channel"));
        dto.setShipDate(LocalDate.parse(jsonObject.getString("ship_date"),formatter));
        dto.setUnitsSold(jsonObject.getInt("units_sold"));
        dto.setUnitPrice(jsonObject.getJsonNumber("unit_price").doubleValue());
        dto.setUnitCost(jsonObject.getJsonNumber("unit_cost").doubleValue());
        dto.setTotalRevenue(jsonObject.getJsonNumber("total_revenue").doubleValue());
        dto.setTotalCost(jsonObject.getJsonNumber("total_cost").doubleValue());
        dto.setTotalProfit(jsonObject.getJsonNumber("total_profit").doubleValue());
        dto.setSelfLink(jsonObject.get("links").asJsonObject().getString("self"));

        return dto;
    }
}
