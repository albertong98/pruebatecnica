package business.orders;

import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

public class PedidoDTO {
    private String orderID;
    private String uuid;
    private String orderPriority;
    private LocalDate orderDate;
    private String region;
    private String country;
    private String itemType;
    private String salesChannel;
    private LocalDate shipDate;
    private int unitsSold;
    private double unitPrice;
    private double unitCost;
    private double totalRevenue;
    private double totalCost;
    private double totalProfit;
    private String selfLink;
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }
    public String getOrderPriority() {
        return orderPriority;
    }

    public void setOrderPriority(String orderPriority) {
        this.orderPriority = orderPriority;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getSalesChannel() {
        return salesChannel;
    }

    public void setSalesChannel(String salesChannel) {
        this.salesChannel = salesChannel;
    }

    public LocalDate getShipDate() {
        return shipDate;
    }

    public void setShipDate(LocalDate shipDate) {
        this.shipDate = shipDate;
    }

    public int getUnitsSold() {
        return unitsSold;
    }

    public void setUnitsSold(int unitsSold) {
        this.unitsSold = unitsSold;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public String getSelfLink(){ return selfLink; }

    public void setSelfLink(String selfLink){ this.selfLink = selfLink; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoDTO pedidoDTO = (PedidoDTO) o;
        return Objects.equals(orderID, pedidoDTO.orderID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID);
    }

    public String toCsvString(){
        StringJoiner sj = new StringJoiner(";");
        sj.add(orderID);
        sj.add(orderPriority);
        sj.add(orderDate.toString());
        sj.add(region);
        sj.add(country);
        sj.add(itemType);
        sj.add(salesChannel);
        sj.add(shipDate.toString());
        sj.add(String.valueOf(unitsSold));
        sj.add(String.valueOf(unitPrice));
        sj.add(String.valueOf(unitCost));
        sj.add(String.valueOf(totalRevenue));
        sj.add(String.valueOf(totalCost));
        sj.add(String.valueOf(totalProfit));
        return sj.toString();
    }
}
