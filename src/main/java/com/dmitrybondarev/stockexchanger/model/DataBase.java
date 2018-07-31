package com.dmitrybondarev.stockexchanger.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Container for all OrderBooks.
 */
public class DataBase {

    private Lock lock = new ReentrantLock();

    private final Map<String, OrderBook> orderBooks = new HashMap<>();

    public void addOrder(Order order) {
        lock.lock();
        String company = order.getCompany();
        if (orderBooks.containsKey(company)) {
            orderBooks.get(company).addOrderToOrderBook(order);
        } else {
            OrderBook newOrderBook = new OrderBook(company);
            newOrderBook.addOrderToOrderBook(order);
            orderBooks.put(company, newOrderBook);
        }
        lock.unlock();
    }

    public boolean removeOrder(String company, int id) {
        lock.lock();
        boolean result = false;
        if (orderBooks.containsKey(company)) {
            result = orderBooks.get(company).removeOrderFromOrderBook(id);
        }
        lock.unlock();
        return result;
    }

    public Collection<OrderBook> getOrderBooks() {
        return orderBooks.values();
    }

    public Lock getLock() {
        return lock;
    }
}
