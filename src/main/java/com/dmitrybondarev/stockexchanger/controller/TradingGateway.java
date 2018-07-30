package com.dmitrybondarev.stockexchanger.controller;

import com.dmitrybondarev.stockexchanger.model.Action;
import com.dmitrybondarev.stockexchanger.model.DataBase;
import com.dmitrybondarev.stockexchanger.model.Order;
import com.dmitrybondarev.stockexchanger.view.CLI;

import java.util.Timer;

public class TradingGateway {

    private DataBase dataBase = DataBase.getDataBase();

    public void addOrder(String company, Action action, int price, int volume){
        Order order;
        if (action == Action.BUY)
            order = Order.createBuyOrder(company, price, volume);
        else {
            order = Order.createSellOrder(company, price, volume);
        }
        dataBase.addOrder(order);
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        MatchingEngine matchingEngine = new MatchingEngine();
        timer.schedule(matchingEngine, 1000, 1000);

        new CLI(new TradingGateway()).start();
        timer.cancel();
    }
}

