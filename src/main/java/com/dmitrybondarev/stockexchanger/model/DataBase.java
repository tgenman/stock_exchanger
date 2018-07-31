package com.dmitrybondarev.stockexchanger.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DataBase {

    private final Map<String, OrderBook> orderBooks = new HashMap<>();

    public void addOrder(Order order) {
        String company = order.getCompany();
        if (orderBooks.containsKey(company)) {
            orderBooks.get(company).addOrderToOrderBook(order);
        } else {
            OrderBook newOrderBook = new OrderBook(company);
            newOrderBook.addOrderToOrderBook(order);
            orderBooks.put(company, newOrderBook);
        }
    }

    public boolean removeOrder(String company, int id) {
        boolean result = false;
        if (orderBooks.containsKey(company)) {
            result = orderBooks.get(company).removeOrderFromOrderBook(id);
        }
        return result;
    }

    public Collection<OrderBook> getOrderBooks() {
        return orderBooks.values();
    }
}
