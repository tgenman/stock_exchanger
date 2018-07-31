package com.dmitrybondarev.stockexchanger.model;

import java.util.LinkedList;
import java.util.List;

public class OrderBook {

    private final String name;

    private final List<Order> buyOrders;

    private final List<Order> sellOrders;

    OrderBook(String name) {
        this.name = name;
        this.buyOrders =  new LinkedList<>();
        this.sellOrders = new LinkedList<>();
    }

    public List<Order> getBuyOrders() {
        return buyOrders;
    }

    public List<Order> getSellOrders() {
        return sellOrders;
    }

    void addOrderToOrderBook(Order order) {
        Action type = order.getActionOfOrder();
        if (type == Action.BUY)
            buyOrders.add(order);
        if (type == Action.SELL)
            sellOrders.add(order);
    }

    boolean removeOrderFromOrderBook(int id) {
        for (Order current: buyOrders) {
            if (current.getId() == id) {
                return buyOrders.remove(current);
            }
        }
        for (Order current: sellOrders) {
            if (current.getId() == id) {
                return sellOrders.remove(current);
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }
}


