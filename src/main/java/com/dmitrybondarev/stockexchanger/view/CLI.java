package com.dmitrybondarev.stockexchanger.view;

import com.dmitrybondarev.stockexchanger.controller.TradingGateway;
import com.dmitrybondarev.stockexchanger.model.Action;
import java.util.Scanner;

public class CLI {

    private Scanner scanner = new Scanner(System.in);

    private TradingGateway tradingGateway;

    public CLI(TradingGateway tradingGateway) {
        this.tradingGateway = tradingGateway;
    }

    public void start() {
        System.out.println("STOCK EXCHANGER");
        System.out.println("Command can be: add, remove, exit");
        System.out.println("Add pattern: Command Company Action Price Volume");
        System.out.println("Action can be: B, S");
        System.out.println("Price and Amount is Integer");
        System.out.println("Example: add Google B 100 50");

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
                        removeCommand();
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
        tradingGateway.addOrder(company, action, price, volume);
    }

    private void removeCommand() {
        System.out.println("remove");
    }

    private Action parseAction(String str) throws Exception{
        if (str.equals("B")) {
            return Action.BUY;
        } else if (str.equals("S")) {
            return Action.SELL;
        } else {
            throw new Exception("Action doesn't find");
        }
    }
}
