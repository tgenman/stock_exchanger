package com.dmitrybondarev.stockexchanger.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test of Trade class.
 */
public class TradeTest {

    @Test
    public void testCreateTradeWhenBuyOrderMoreThanSellOrder() {

//      Input values
        Order buyOrder = Order.createBuyOrder("test", 110, 60);
        Order sellOrder = Order.createSellOrder("test", 100, 100);

//      Test class
        Trade trade = new Trade(buyOrder, sellOrder);

//      assert
        Assert.assertEquals(0, buyOrder.getVolume());
        Assert.assertEquals(40, sellOrder.getVolume());
    }

    @Test
    public void testCreateTradeWhenSellOrderMoreThanBuyOrder() {

//      Input values
        Order buyOrder = Order.createBuyOrder("test", 110, 150);
        Order sellOrder = Order.createSellOrder("test", 100, 60);

//      Test class
        Trade trade = new Trade(buyOrder, sellOrder);

//      assert
        Assert.assertEquals(90, buyOrder.getVolume());
        Assert.assertEquals(0, sellOrder.getVolume());
    }

    @Test
    public void testCreateTradeWhenSellOrderEqualBuyOrder() {

//      Input values
        Order buyOrder = Order.createBuyOrder("test", 110, 150);
        Order sellOrder = Order.createSellOrder("test", 100, 150);

//      Test class
        Trade trade = new Trade(buyOrder, sellOrder);

//      assert
        Assert.assertEquals(0, buyOrder.getVolume());
        Assert.assertEquals(0, sellOrder.getVolume());
    }
}