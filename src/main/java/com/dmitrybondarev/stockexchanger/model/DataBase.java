package com.dmitrybondarev.stockexchanger.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DataBase {

    private final Map<String, OrderBook> orderBooks = new HashMap<>();

    public void addOrder(Order order) {
        String nameCompany = order.getCompany();
        if (orderBooks.containsKey(nameCompany)) {
            orderBooks.get(nameCompany).addOrderToOrderBook(order);
        } else {
            OrderBook newOrderBook = new OrderBook(nameCompany);
            newOrderBook.addOrderToOrderBook(order);
            orderBooks.put(nameCompany, newOrderBook);
        }
    }



    public Order removeOrder(String company, int id) {
        return orderBooks.get(company).removeOrderFromOrderBook(id);
    }

    public Collection<OrderBook> getOrderBooks() {
        return orderBooks.values();
    }
}
