package com.dmitrybondarev.stockexchanger.model;

import java.util.ArrayList;
import java.util.List;

public class TradeLedger {

    private static TradeLedger tradeLedger;

    private final List<Trade> tradeLog = new ArrayList<>();

    private TradeLedger() {}

    public static TradeLedger getTradeLedger() {
        if (tradeLedger == null) tradeLedger = new TradeLedger();
        return tradeLedger;
    }

    public void addTrade(Trade trade) {
        System.out.println(trade.toString());
        tradeLog.add(trade);
    }
}
