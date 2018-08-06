package com.dmitrybondarev.stockexchanger.controller;

import com.dmitrybondarev.stockexchanger.model.DataBase;
import com.dmitrybondarev.stockexchanger.model.Order;
import com.dmitrybondarev.stockexchanger.model.OrderBook;
import com.dmitrybondarev.stockexchanger.model.Trade;
import com.dmitrybondarev.stockexchanger.model.TradeLedger;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;

/**
 * Balances the order books periodically.
 */
public class MatchingEngine extends TimerTask {

    private DataBase dataBase;

    private TradeLedger tradeLedger;

    private Lock dataBaseLock;

    MatchingEngine(DataBase dataBase, TradeLedger tradeLedger) {
        this.dataBase = dataBase;
        this.tradeLedger = tradeLedger;
        this.dataBaseLock = dataBase.getLock();
    }

    @Override
    public void run() {
        dataBaseLock.lock();
        Collection<OrderBook> orderBooks = dataBase.getOrderBooks();
        for (OrderBook orderBook: orderBooks) {
            matchOrdersInOrderBook(orderBook.getBuyOrders(), orderBook.getSellOrders());
        }
        dataBaseLock.unlock();
    }

    private void matchOrdersInOrderBook(Set<Order> buyOrders, Set<Order> sellOrders) {
        if (buyOrders.isEmpty()
                || sellOrders.isEmpty()) return;

        Iterator<Order> sellIterator = sellOrders.iterator();

        while (sellIterator.hasNext()) {
            Order sellOrder = sellIterator.next();
            Iterator<Order> buyIterator = buyOrders.iterator();

            while (buyIterator.hasNext()) {
                Order buyOrder = buyIterator.next();
                if (buyOrder.getPrice() >= sellOrder.getPrice()) {
                    tradeLedger.addTrade(new Trade(buyOrder, sellOrder));

                    // Remove executed Orders
                    if (buyOrder.getVolume() == 0) {
                        buyIterator.remove();
                    }
                    if (sellOrder.getVolume() == 0) {
                        sellIterator.remove();
                        break;
                    }
                }
            }
        }
    }
}


