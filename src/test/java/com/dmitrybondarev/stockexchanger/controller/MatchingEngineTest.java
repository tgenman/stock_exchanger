package com.dmitrybondarev.stockexchanger.controller;

import com.dmitrybondarev.stockexchanger.model.DataBase;
import com.dmitrybondarev.stockexchanger.model.Order;
import com.dmitrybondarev.stockexchanger.model.OrderBook;
import com.dmitrybondarev.stockexchanger.model.TradeLedger;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test of MatchingEngine class.
 */
public class MatchingEngineTest {

    @Test
    public void testMatching1() {
//      Input values
        Order buyOrder1 = Order.createBuyOrder("test", 100, 40);
        Order buyOrder2 = Order.createBuyOrder("test", 103, 70);
        Order sellOrder1 = Order.createSellOrder("test", 90, 100);
        Order sellOrder2 = Order.createSellOrder("test", 105, 50);

//      Initialization
        DataBase dataBase = new DataBase();
        dataBase.addOrder(buyOrder1);
        dataBase.addOrder(buyOrder2);
        dataBase.addOrder(sellOrder1);
        dataBase.addOrder(sellOrder2);

//      Test class
        TradeLedger tradeLedger = new TradeLedger();
        MatchingEngine matchingEngine = new MatchingEngine(dataBase, tradeLedger);

//      run
        matchingEngine.run();

//      assert
        OrderBook testOrderBook = dataBase.getOrderBooks().iterator().next();

        Assert.assertEquals(1, testOrderBook.getBuyOrders().size());
        Assert.assertEquals(1, testOrderBook.getSellOrders().size());
    }

    @Test
    public void testMatching2() {
//      Input values
        Order buyOrder1 = Order.createBuyOrder("test", 100, 40);
        Order buyOrder2 = Order.createBuyOrder("test", 103, 70);
        Order sellOrder1 = Order.createSellOrder("test", 90, 500);
        Order sellOrder2 = Order.createSellOrder("test", 105, 50);

//      Initialization
        DataBase dataBase = new DataBase();
        dataBase.addOrder(buyOrder1);
        dataBase.addOrder(buyOrder2);
        dataBase.addOrder(sellOrder1);
        dataBase.addOrder(sellOrder2);

//      Test class
        TradeLedger tradeLedger = new TradeLedger();
        MatchingEngine matchingEngine = new MatchingEngine(dataBase, tradeLedger);

//      run
        matchingEngine.run();

//      assert
        OrderBook testOrderBook = dataBase.getOrderBooks().iterator().next();

        Assert.assertEquals(0, testOrderBook.getBuyOrders().size());
        Assert.assertEquals(2, testOrderBook.getSellOrders().size());
    }

    @Test
    public void testMatching3() {
//      Input values
        Order buyOrder1 = Order.createBuyOrder("test", 100, 40);
        Order buyOrder2 = Order.createBuyOrder("test", 103, 70);
        Order buyOrder3 = Order.createBuyOrder("test", 50, 40);
        Order buyOrder4 = Order.createBuyOrder("test", 500, 500);
        Order sellOrder1 = Order.createSellOrder("test", 90, 110);
        Order sellOrder2 = Order.createSellOrder("test", 105, 50);
        Order sellOrder3 = Order.createSellOrder("test", 90, 100);
        Order sellOrder4 = Order.createSellOrder("test", 501, 1);

//      Initialization
        DataBase dataBase = new DataBase();
        dataBase.addOrder(buyOrder1);
        dataBase.addOrder(buyOrder2);
        dataBase.addOrder(buyOrder3);
        dataBase.addOrder(buyOrder4);
        dataBase.addOrder(sellOrder1);
        dataBase.addOrder(sellOrder2);
        dataBase.addOrder(sellOrder3);
        dataBase.addOrder(sellOrder4);

//      Test class
        TradeLedger tradeLedger = new TradeLedger();
        MatchingEngine matchingEngine = new MatchingEngine(dataBase, tradeLedger);

//      run
        matchingEngine.run();

//      assert
        OrderBook testOrderBook = dataBase.getOrderBooks().iterator().next();

        Assert.assertEquals(2, testOrderBook.getBuyOrders().size());
        Assert.assertEquals(1, testOrderBook.getSellOrders().size());
    }


}