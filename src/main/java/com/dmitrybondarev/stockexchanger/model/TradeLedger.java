package com.dmitrybondarev.stockexchanger.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains all trades that happen on all books.
 */
public class TradeLedger {

    private final List<Trade> tradeLog = new ArrayList<>();

    public void addTrade(Trade trade) {
        System.out.println(trade.toString());
        tradeLog.add(trade);
    }

    public List<Trade> getTradeLog() {
        return tradeLog;
    }
}
