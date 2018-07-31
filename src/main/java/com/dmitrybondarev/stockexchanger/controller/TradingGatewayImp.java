package com.dmitrybondarev.stockexchanger.controller;

import com.dmitrybondarev.stockexchanger.model.Action;
import com.dmitrybondarev.stockexchanger.model.DataBase;
import com.dmitrybondarev.stockexchanger.model.Order;
import com.dmitrybondarev.stockexchanger.model.TradeLedger;
import com.dmitrybondarev.stockexchanger.view.CLI;

import java.util.Timer;

public class TradingGatewayImp implements TradingGateway {

    private DataBase dataBase;

    public TradingGatewayImp(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public Order addOrder(String company, Action action, int price, int volume) {
        Order result;
        if (action == Action.BUY)
            result = Order.createBuyOrder(company, price, volume);
        else {
            result = Order.createSellOrder(company, price, volume);
        }
        dataBase.addOrder(result);
        return result;
    }

    @Override
    public boolean removeOrder(String company, int id) {
        return dataBase.removeOrder(company, id);
    }

    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        TradeLedger tradeLedger = new TradeLedger();
        MatchingEngine matchingEngine = new MatchingEngine(dataBase, tradeLedger);

        Timer timer = new Timer();
        timer.schedule(matchingEngine, 1000, 1000);

        new CLI(new TradingGatewayImp(dataBase)).start();
        timer.cancel();
    }
}

