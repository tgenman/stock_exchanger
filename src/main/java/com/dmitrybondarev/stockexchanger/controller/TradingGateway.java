package com.dmitrybondarev.stockexchanger.controller;

import com.dmitrybondarev.stockexchanger.model.Action;
import com.dmitrybondarev.stockexchanger.model.Order;

public interface TradingGateway {

    Order addOrder(String company, Action action, int price, int volume);

    boolean removeOrder(String company, int id);
}
