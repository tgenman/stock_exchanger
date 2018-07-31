package com.dmitrybondarev.stockexchanger.model;

import java.util.ArrayList;
import java.util.List;

public class TradeLedger {

    private final List<Trade> tradeLog = new ArrayList<>();

    public TradeLedger() {}

    public void addTrade(Trade trade) {
        System.out.println(trade.toString());
        tradeLog.add(trade);
    }
}
