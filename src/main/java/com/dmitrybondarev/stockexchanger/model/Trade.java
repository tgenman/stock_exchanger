package com.dmitrybondarev.stockexchanger.model;

import com.dmitrybondarev.stockexchanger.model.Order;

import java.util.Date;

public class Trade {

    private static int counterIDTrade = 1;

    private final int idTrade;

    private final Date date;

    private final Order buyOrder;

    private final Order sellOrder;

    public Trade(Order buyOrder, Order sellOrder) {
        this.idTrade = counterIDTrade++;
        this.date = null;                           //Add working date
        this.buyOrder = buyOrder;
        this.sellOrder = sellOrder;
    }

    public String toString() {
        return "Empty String";
    }
}
