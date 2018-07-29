package com.dmitrybondarev.stockexchanger;

public interface TradingGateway {

    boolean addOrder(Order order);
}
