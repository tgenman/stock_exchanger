package com.dmitrybondarev.stockexchanger.view;

import com.dmitrybondarev.stockexchanger.controller.TradingGateway;
import com.dmitrybondarev.stockexchanger.model.Action;
import com.dmitrybondarev.stockexchanger.model.Order;

import java.util.Scanner;

/**
 * Command Line Interface.
 */
public class CLI implements Runnable {

    private Scanner scanner = new Scanner(System.in);

    private TradingGateway tradingGateway;

    public CLI(TradingGateway tradingGateway) {
        this.tradingGateway = tradingGateway;
    }

    @Override
    public void run() {
        System.out.println("STOCK EXCHANGER");
        System.out.println("Command can be: add, remove, exit");
        System.out.println();
        System.out.println("add pattern: add Company Action Price Volume");
        System.out.println("Action can be: B, S");
        System.out.println("Price and Amount is Integer");
        System.out.println("Example: add Google B 100 50");
        System.out.println();
        System.out.println("remove pattern: remove Company ID");

        boolean isWorking = true;
        while (isWorking) {
            try {
                String[] query = scanner.nextLine().split(" ");
                String command = query[0];
                switch (command) {
                    case "exit":
                        isWorking = false;
                        break;
                    case "add":
                        addCommand(query);
                        break;
                    case "remove":
                        removeCommand(query);
                        break;
                    default:
                        System.out.println("Invalid query. Try again");
                }
            } catch (Exception e) {
                System.out.println("Invalid query. Try again");
            }
        }
        System.out.println("Program was exit.");
    }

    private void addCommand(String[] query) throws Exception {
        String company = query[1];
        Action action = parseAction(query[2]);
        int price = Integer.parseInt(query[3]);
        int volume = Integer.parseInt(query[4]);
        Order result = tradingGateway.addOrder(company, action, price, volume);
        System.out.println("added " + result.toString());
    }

    private void removeCommand(String[] query) {
        String company = query[1];
        int id = Integer.parseInt(query[2]);
        boolean result = tradingGateway.removeOrder(company, id);
        if (result) {
            System.out.println("Order ID = " + id + " wasn't found.");
        } else {
            System.out.println("Order ID = " + id + " was removed.");
        }
    }

    private Action parseAction(String str) throws Exception {
        if (str.equals("B")) {
            return Action.BUY;
        } else if (str.equals("S")) {
            return Action.SELL;
        } else {
            throw new Exception("Action doesn't find");
        }
    }
}
