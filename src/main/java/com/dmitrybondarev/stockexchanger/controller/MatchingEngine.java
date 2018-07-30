package com.dmitrybondarev.stockexchanger.controller;

import com.dmitrybondarev.stockexchanger.model.DataBase;
import com.dmitrybondarev.stockexchanger.model.Order;
import com.dmitrybondarev.stockexchanger.model.OrderBook;
import com.dmitrybondarev.stockexchanger.model.Trade;
import com.dmitrybondarev.stockexchanger.model.TradeLedger;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

public class MatchingEngine extends TimerTask {

    private DataBase dataBase = DataBase.getDataBase();

    private TradeLedger tradeLedger = TradeLedger.getTradeLedger();

    @Override
    public void run() {
        Collection<OrderBook> orderBooks = dataBase.getOrderBooks();
        for (OrderBook orderBook: orderBooks) {
            matchOrdersInOrderBook(orderBook.getBuyOrders(), orderBook.getSellOrders());
        }
    }

    private void matchOrdersInOrderBook(List<Order> buyOrders, List<Order> sellOrders) {
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






//        for (Order buyOrder: buyOrders) {
//            if (buyOrder.getVolume() == 0) continue;
//            for (Order sellOrder: sellOrders) {
//                if (sellOrder.getVolume() == 0) continue;
//                if (buyOrder.getPrice() < sellOrder.getPrice()) {
//                    break;
//                }
//                if (sellOrder.getPrice() <= buyOrder.getPrice()) {
//                    tradeLedger.addTrade(new Trade(buyOrder, sellOrder));
//                }
//            }
//        }
    }
}


