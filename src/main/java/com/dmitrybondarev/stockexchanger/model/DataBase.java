package com.dmitrybondarev.stockexchanger.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DataBase {

    private static DataBase dataBase;

    private final Map<String, OrderBook> orderBooks = new HashMap<>();

    private DataBase() {}

    public static DataBase getDataBase() {
        if (dataBase == null) dataBase = new DataBase();
        return dataBase;
    }

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

//    public boolean removeOrder(Order order) {
//        boolean result = false;
//        if (orderBooks.containsKey(order.getCompany())) {
//            OrderBook orderBook = orderBooks.get(order.getCompany());
//            orderBook.removeOrderFromOrderBook(order);
//            result = true;
//        }
//        return result;
//    }

    public Collection<OrderBook> getOrderBooks() {
        return orderBooks.values();
    }
}
