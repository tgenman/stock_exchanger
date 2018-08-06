package com.dmitrybondarev.stockexchanger.model;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import java.util.Set;

/**
 * Test of OrderBook class.
 */
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
        Set<Order> buyOrders = orderBook.getBuyOrders();
        Set<Order> sellOrders = orderBook.getSellOrders();

        Assert.assertEquals(2, buyOrders.size());
        Assert.assertEquals(2, sellOrders.size());
    }

    @Test
    public void testRemoveOrdersToOrderBooksWhenOrderBookHaveOrder() {
//      Input values
        Order buyOrder1 = Order.createBuyOrder("test", 100, 100);
        Order buyOrder2 = Order.createBuyOrder("test", 100, 100);

//      Test class
        OrderBook orderBook = new OrderBook("test");

//      run
        orderBook.addOrderToOrderBook(buyOrder1);
        orderBook.addOrderToOrderBook(buyOrder2);
        boolean result = orderBook.removeOrderFromOrderBook(buyOrder1.getId());

//      assert
        Set<Order> buyOrders = orderBook.getBuyOrders();

        Assert.assertTrue(result);
        Assert.assertEquals(1, buyOrders.size());
    }

    @Test
    public void testRemoveOrdersToOrderBooksWhenOrderBookHaveNotOrder() {
//      Input values
        Order buyOrder1 = Order.createBuyOrder("test", 100, 100);
        Order buyOrder2 = Order.createBuyOrder("test", 100, 100);

//      Test class
        OrderBook orderBook = new OrderBook("test");

//      run
        orderBook.addOrderToOrderBook(buyOrder1);
        orderBook.addOrderToOrderBook(buyOrder2);
        boolean result = orderBook.removeOrderFromOrderBook(350);

//      assert
        Set<Order> buyOrders = orderBook.getBuyOrders();

        Assert.assertFalse(result);
        Assert.assertEquals(2, buyOrders.size());
    }
}