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
        System.out.println("added " + order.toString());
    }

//    void removeOrderFromOrderBook(Order order) {
//        boolean result = false;
//        Action type = order.getActionOfOrder();
//        if (type == Action.BUY)
//            result = buyOrders.remove(order);
//        if (type == Action.SELL)
//            result = sellOrders.remove(order);
//        if (result)
//            System.out.println("Order removed " + order.toString());
//    }


    public String getName() {
        return name;
    }
}


