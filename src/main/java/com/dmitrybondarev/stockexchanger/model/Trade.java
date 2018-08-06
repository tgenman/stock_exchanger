package com.dmitrybondarev.stockexchanger.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represent the details of two opposite orders crossing with each other.
 */
public class Trade {

    private final static SimpleDateFormat SIMPLE_DATE_FORMAT
            = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss a zzz");

    private static int counterIDTrade = 1;

    private final int idTrade;

    private final Date date;

    private final String nameOfCompany;

    private final int priceOfTrade;

    private final int volumeOfTrade;

    private final int buyOrderId;

    private final int sellOrderId;

    /**
     * Create Trade and change order's volume.
     * @param buyOrder Order
     * @param sellOrder Order
     */
    public Trade(Order buyOrder, Order sellOrder) {
        this.idTrade = counterIDTrade++;
        this.date = new Date();
        this.nameOfCompany = buyOrder.getCompany();
        this.buyOrderId = buyOrder.getId();
        this.sellOrderId = sellOrder.getId();
        this.priceOfTrade = sellOrder.getPrice();
        this.volumeOfTrade = calculateVolumeOfTrade(buyOrder, sellOrder);
        buyOrder.setVolume(buyOrder.getVolume() - volumeOfTrade);
        sellOrder.setVolume(sellOrder.getVolume() - volumeOfTrade);
    }

    public String toString() {
        return SIMPLE_DATE_FORMAT.format(date)
                + " New execution with ID " + this.idTrade + ": "
                + nameOfCompany + " "
                + priceOfTrade + "RUB "
                + volumeOfTrade + "pcs (orders ID "
                + buyOrderId + " and "
                + sellOrderId + ")";
    }

    private int calculateVolumeOfTrade(Order buyOrder, Order sellOrder) {
        if (buyOrder.getVolume() >= sellOrder.getVolume())
            return sellOrder.getVolume();
        else
            return buyOrder.getVolume();
    }

    public int getPriceOfTrade() {
        return priceOfTrade;
    }
}
