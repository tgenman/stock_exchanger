package com.dmitrybondarev.stockexchanger.controller;

import com.dmitrybondarev.stockexchanger.model.Action;
import com.dmitrybondarev.stockexchanger.model.Order;

/**
 * Allows a client to trade on the market.
 */
public interface TradingGateway {

    /**
     * Create and add Order to DataBase.
     * @param company name of a Company
     * @param action Buy or Sell
     * @param price of Order
     * @param volume of Order
     * @return Created Order
     */
    Order addOrder(String company, Action action, int price, int volume);

    /**
     * Remove order from DataBase.
     * @param company name of a Company
     * @param id int
     * @return boolean result of action
     */
    boolean removeOrder(String company, int id);
}
