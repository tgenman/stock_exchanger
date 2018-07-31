package com.dmitrybondarev.stockexchanger.controller;

import com.dmitrybondarev.stockexchanger.model.Action;

public interface TradingGateway {

    void addOrder(String company, Action action, int price, int volume);
}
