package com.dmitrybondarev.stockexchanger.controller;

import com.dmitrybondarev.stockexchanger.model.Order;

public interface TradingGateway {

    int addOrder(Order order);

    boolean cancelOrder(int id);
}
