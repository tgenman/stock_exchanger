package com.dmitrybondarev.stockexchanger.controller;

import com.dmitrybondarev.stockexchanger.model.Action;
import com.dmitrybondarev.stockexchanger.model.DataBase;
import com.dmitrybondarev.stockexchanger.model.Order;
import com.dmitrybondarev.stockexchanger.model.TradeLedger;
import com.dmitrybondarev.stockexchanger.view.CLI;

import java.util.Timer;

/**
 * Allows a client to trade on the market.
 */
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

//      Create and start CLI Thread
        Runnable cLIRunnable = new CLI(new TradingGatewayImp(dataBase));
        Thread cLIThread = new Thread(cLIRunnable);
        cLIThread.start();

//      Create MachineEngine and start Thread with timer
        MatchingEngine matchingEngine = new MatchingEngine(dataBase, tradeLedger);
        Timer timer = new Timer();
        timer.schedule(matchingEngine, 1000, 1000);

//      Closing program
        try {
            cLIThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
    }
}

