package com.dmitrybondarev.stockexchanger.model;

import org.junit.Assert;
import org.junit.Test;
import java.util.Collection;

/**
 * Test of DataBase class.
 */
public class DataBaseTest {

    @Test
    public void testAddOrder() {
//      Input values
        Order order1 = Order.createBuyOrder("test01", 100, 100);
        Order order2 = Order.createBuyOrder("test02", 100, 100);
        Order order3 = Order.createSellOrder("tes03", 100, 100);
        Order order4 = Order.createSellOrder("test01", 100, 100);

//      Test class
        DataBase dataBase = new DataBase();

//      run
        dataBase.addOrder(order1);
        dataBase.addOrder(order2);
        dataBase.addOrder(order3);
        dataBase.addOrder(order4);

//      assert
        Collection<OrderBook> result = dataBase.getOrderBooks();
        Assert.assertEquals(3, result.size());
    }

    @Test
    public void testRemoveOrderWhenDatabaseHaveCompany() {
//      Input values
        Order order1 = Order.createBuyOrder("test", 100, 100);
        Order order2 = Order.createBuyOrder("test", 100, 100);

//      Test class
        DataBase dataBase = new DataBase();

//      run
        dataBase.addOrder(order1);
        dataBase.addOrder(order2);
        boolean result = dataBase.removeOrder("test", order1.getId());

//      assert
        Assert.assertTrue(result);
    }

    @Test
    public void testRemoveOrderWhenDatabaseHaveNotCompany() {
//      Input values
        Order order1 = Order.createBuyOrder("test", 100, 100);
        Order order2 = Order.createBuyOrder("test", 100, 100);

//      Test class
        DataBase dataBase = new DataBase();

//      run
        dataBase.addOrder(order1);
        dataBase.addOrder(order2);
        boolean result = dataBase.removeOrder("tests", order1.getId());

//      assert
        Assert.assertFalse(result);
    }
}