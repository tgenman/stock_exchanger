package com.dmitrybondarev.stockexchanger.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class OrderBookTest {

    @Test
    public void testAddOrdersToOrderBooks() {
//      Input values
        Order buyOrder1 = Order.createBuyOrder("test", 100, 100);
        Order buyOrder2 = Order.createBuyOrder("test", 100, 100);
        Order sellOrder1 = Order.createSellOrder("test", 100, 100);
        Order sellOrder2 = Order.createSellOrder("test", 100, 100);

//      Test class
        OrderBook orderBook = new OrderBook("Test");

//      run
        orderBook.addOrderToOrderBook(buyOrder1);
        orderBook.addOrderToOrderBook(buyOrder2);
        orderBook.addOrderToOrderBook(sellOrder1);
        orderBook.addOrderToOrderBook(sellOrder2);

//      assert
        List<Order> buyOrders = orderBook.getBuyOrders();
        List<Order> sellOrders = orderBook.getSellOrders();

        Assert.assertEquals(2, buyOrders.size());
        Assert.assertEquals(2, sellOrders.size());
    }

}