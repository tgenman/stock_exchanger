package com.dmitrybondarev.stockexchanger.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Contain all orders for a certain stock.
 */
public class OrderBook {

    private final String name;

    private final Set<Order> buyOrders;

    private final Set<Order> sellOrders;

    OrderBook(String name) {
        this.name = name;

        //Sort from smaller price to bigger
        this.buyOrders =  new TreeSet<>(
                (order1, order2) -> {
                    if (order1.getId() == order2.getId()) {
                        return 0;
                    } else if (order1.getPrice() > order2.getPrice()) {
                        return  -1;
                    } else {
                        return  1;
                    }
                }
        );

//      Sort from bigger price to smaller
        this.sellOrders = new TreeSet<>(
                (order1, order2) -> {
                    if (order1.getId() == order2.getId()) {
                        return 0;
                    } else if (order1.getPrice() < order2.getPrice()) {
                        return  -1;
                    } else {
                        return  1;
                    }
                }
        );
    }

    public Set<Order> getBuyOrders() {
        return buyOrders;
    }

    public Set<Order> getSellOrders() {
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
}


