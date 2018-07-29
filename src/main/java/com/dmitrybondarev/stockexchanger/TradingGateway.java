package com.dmitrybondarev.stockexchanger;

public interface TradingGateway {

    int addOrder(Order order);

    boolean cancelOrder(int id);
}
