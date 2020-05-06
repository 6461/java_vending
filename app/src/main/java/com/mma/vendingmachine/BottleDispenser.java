package com.mma.vendingmachine;

import java.util.ArrayList;

public class BottleDispenser {
    private static BottleDispenser instance = null;
    private int money = 0; // this is in cents! 100 cnt = 1 euro
    public ArrayList<Bottle> list;

    public static BottleDispenser getInstance() {
        if (instance == null) {
            instance = new BottleDispenser();
        }

        return instance;
    }

    private BottleDispenser() {
        // bottleCount = 6;
        money = 0;
        list = new ArrayList();

        list.add(new Bottle("Pepsi Max", "Pepsi", 0.5f, 180, 1));
        list.add(new Bottle("Pepsi Max", "Pepsi", 1.5f, 220, 1));
        list.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 0.5f, 200, 1));
        list.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 1.5f, 250, 1));
        list.add(new Bottle("Fanta Zero", "Coca-Cola", 0.5f, 195, 2));
    }

    public void insertMoney(int m) {
        money += m;
    }

    public int getMoney() {
        return money;
    }

    public void returnMoney() {
        money = 0;
    }

    public boolean purchase(Bottle bottle) {
        boolean status = false;

        if (money < bottle.getPrice()) {
            System.out.println("Syötä rahaa ensin!");
        }
        else if (bottle.getCount() == 0) {
            System.out.println("Pullot loppu!");
        }
        else {
            bottle.decrease();
            money = money - bottle.getPrice();
            status = true;
            if (bottle.getCount() < 1) {
                list.remove(bottle);
            }
            System.out.printf("KACHUNK! %s tipahti masiinasta!\n", bottle.getName());
        }

        return status;
    }

    public ArrayList<Bottle> getList() {
        return list;
    }
}
