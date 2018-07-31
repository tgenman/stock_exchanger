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

    Order removeOrderFromOrderBook(int id) {
        for (Order order: buyOrders) {
            if (order.getIdOrder() == id) {
                return order;
            }
        }
        for (Order order: sellOrders) {
            if (order.getIdOrder() == id) {
                return order;
            }
        }
        return null;
    }


    public String getName() {
        return name;
    }
}


