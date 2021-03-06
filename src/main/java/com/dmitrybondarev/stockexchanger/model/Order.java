package com.dmitrybondarev.stockexchanger.model;

import java.util.Objects;

/**
 * Order for buying or selling stocks.
 */
public class Order {

    private static int counterIdOrder = 1;

    private final int idOrder;

    private final String company;

    private final Action actionOfOrder;

    private final int price;

    private int volume;

    private Order(String company, Action actionOfOrder, int price, int volume) {
        this.idOrder = counterIdOrder++;
        this.company = company;
        this.actionOfOrder = actionOfOrder;
        this.price = price;
        this.volume = volume;
    }

    public static Order createBuyOrder(String company, int price, int volume) {
        return new Order(company, Action.BUY, price, volume);
    }

    public static Order createSellOrder(String company, int price, int volume) {
        return new Order(company, Action.SELL, price, volume);
    }

    int getId() {
        return idOrder;
    }

    String getCompany() {
        return company;
    }

    Action getActionOfOrder() {
        return actionOfOrder;
    }

    public int getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }

    void setVolume(int volume) {
        this.volume = volume;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return idOrder == order.idOrder
                && price == order.price
                && volume == order.volume
                && Objects.equals(company, order.company)
                && actionOfOrder == order.actionOfOrder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, company, actionOfOrder, price, volume);
    }

    @Override
    public String toString() {
        return "Order: id=" + idOrder
                + " " + company
                + " " + actionOfOrder
                + " price=" + price
                + " volume=" + volume;
    }
}
