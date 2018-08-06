package com.dmitrybondarev.stockexchanger.controller;

import com.dmitrybondarev.stockexchanger.model.DataBase;
import com.dmitrybondarev.stockexchanger.model.Order;
import com.dmitrybondarev.stockexchanger.model.OrderBook;
import com.dmitrybondarev.stockexchanger.model.Trade;
import com.dmitrybondarev.stockexchanger.model.TradeLedger;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;

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

    @Test
    public void testMatching4() {
//      Input values
        Order buyOrder1 = Order.createBuyOrder("test", 51, 10);
        Order buyOrder2 = Order.createBuyOrder("test", 52, 10);
        Order buyOrder3 = Order.createBuyOrder("test", 50, 10);
        Order sellOrder1 = Order.createSellOrder("test", 51, 10);

//      Initialization
        DataBase dataBase = new DataBase();
        dataBase.addOrder(buyOrder1);
        dataBase.addOrder(buyOrder2);
        dataBase.addOrder(buyOrder3);
        dataBase.addOrder(sellOrder1);

//      Test class
        TradeLedger tradeLedger = new TradeLedger();
        MatchingEngine matchingEngine = new MatchingEngine(dataBase, tradeLedger);

//      run
        matchingEngine.run();

//      assert
        OrderBook testOrderBook = dataBase.getOrderBooks().iterator().next();
        Set<Order> buyOrders = testOrderBook.getBuyOrders();
        Set<Order> sellOrders = testOrderBook.getSellOrders();

        Assert.assertEquals(2, buyOrders.size());
        Assert.assertEquals(0, sellOrders.size());

        Order[] buyOrdersArray = buyOrders.toArray(new Order[1]);

        Assert.assertEquals(50, buyOrdersArray[0].getPrice());
        Assert.assertEquals(52, buyOrdersArray[1].getPrice());
    }

    @Test
    public void testMatching5() {
//      Input values
        Order sellOrder1 = Order.createSellOrder("test", 52, 10);
        Order sellOrder2 = Order.createSellOrder("test", 51, 10);
        Order sellOrder3 = Order.createSellOrder("test", 50, 10);
        Order sellOrder4 = Order.createSellOrder("test", 65, 10);
        Order sellOrder5 = Order.createSellOrder("test", 48, 10);
        Order buyOrder1 = Order.createBuyOrder("test", 51, 10);
        Order buyOrder2 = Order.createBuyOrder("test", 64, 10);
        Order buyOrder3 = Order.createBuyOrder("test", 49, 10);

//      Initialization
        DataBase dataBase = new DataBase();
        dataBase.addOrder(sellOrder1);
        dataBase.addOrder(sellOrder2);
        dataBase.addOrder(sellOrder3);
        dataBase.addOrder(sellOrder4);
        dataBase.addOrder(sellOrder5);
        dataBase.addOrder(buyOrder1);
        dataBase.addOrder(buyOrder2);
        dataBase.addOrder(buyOrder3);

//      Test class
        TradeLedger tradeLedger = new TradeLedger();
        MatchingEngine matchingEngine = new MatchingEngine(dataBase, tradeLedger);

//      run
        matchingEngine.run();

//      assert
        OrderBook testOrderBook = dataBase.getOrderBooks().iterator().next();
        Set<Order> buyOrders = testOrderBook.getBuyOrders();
        Set<Order> sellOrders = testOrderBook.getSellOrders();

        Assert.assertEquals(0, buyOrders.size());
        Assert.assertEquals(2, sellOrders.size());

        Order[] sellOrdersArray = sellOrders.toArray(new Order[1]);

        Assert.assertEquals(65, sellOrdersArray[0].getPrice());
        Assert.assertEquals(50, sellOrdersArray[1].getPrice());

        List<Trade> tradeLog = tradeLedger.getTradeLog();

        Assert.assertEquals(52, tradeLog.get(0).getPriceOfTrade());
        Assert.assertEquals(51, tradeLog.get(1).getPriceOfTrade());
        Assert.assertEquals(48, tradeLog.get(2).getPriceOfTrade());
    }

    @Test
    public void testMatching6() {
//      Input values
        Order sellOrder1 = Order.createSellOrder("test", 63, 10);
        Order sellOrder2 = Order.createSellOrder("test", 48, 10);
        Order sellOrder3 = Order.createSellOrder("test", 52, 10);
        Order sellOrder4 = Order.createSellOrder("test", 69, 10);
        Order buyOrder1 = Order.createBuyOrder("test", 65, 10);
        Order buyOrder2 = Order.createBuyOrder("test", 59, 10);
        Order buyOrder3 = Order.createBuyOrder("test", 67, 10);
        Order buyOrder4 = Order.createBuyOrder("test", 56, 10);
        Order buyOrder5 = Order.createBuyOrder("test", 68, 10);
        Order buyOrder6 = Order.createBuyOrder("test", 49, 10);

//      Initialization
        DataBase dataBase = new DataBase();
        dataBase.addOrder(sellOrder1);
        dataBase.addOrder(sellOrder2);
        dataBase.addOrder(sellOrder3);
        dataBase.addOrder(sellOrder4);
        dataBase.addOrder(buyOrder1);
        dataBase.addOrder(buyOrder2);
        dataBase.addOrder(buyOrder3);
        dataBase.addOrder(buyOrder4);
        dataBase.addOrder(buyOrder5);
        dataBase.addOrder(buyOrder6);

//      Test class
        TradeLedger tradeLedger = new TradeLedger();
        MatchingEngine matchingEngine = new MatchingEngine(dataBase, tradeLedger);

//      run
        matchingEngine.run();

//      assert
        OrderBook testOrderBook = dataBase.getOrderBooks().iterator().next();
        Set<Order> buyOrders = testOrderBook.getBuyOrders();
        Set<Order> sellOrders = testOrderBook.getSellOrders();

        Assert.assertEquals(3, buyOrders.size());
        Assert.assertEquals(1, sellOrders.size());

        Order[] sellOrdersArray = sellOrders.toArray(new Order[1]);
        Order[] buyOrdersArray = buyOrders.toArray(new Order[1]);

        Assert.assertEquals(69, sellOrdersArray[0].getPrice());
        Assert.assertEquals(59, buyOrdersArray[0].getPrice());
        Assert.assertEquals(67, buyOrdersArray[1].getPrice());
        Assert.assertEquals(68, buyOrdersArray[2].getPrice());

        List<Trade> tradeLog = tradeLedger.getTradeLog();

        Assert.assertEquals(63, tradeLog.get(0).getPriceOfTrade());
        Assert.assertEquals(52, tradeLog.get(1).getPriceOfTrade());
        Assert.assertEquals(48, tradeLog.get(2).getPriceOfTrade());
    }
}