package com.mma.vendingmachine;

import androidx.annotation.NonNull;

public class Bottle {
    private String name;
    private String manufacturer;
    private float volume;
    private int price; // this is in cents! 100 cnt = 1 euro
    private int count;

    public Bottle () {
        name = "Pepsi Max";
        manufacturer = "Pepsi";
        volume = 0.50f;
        price = 180;
    }

    public Bottle (String name, String manufacturer, float volume, int price, int count) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.volume = volume;
        this.price = price;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public float getVolume() {
        return volume;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public void decrease() {
        this.count--;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%s (%.1f L) %.2f EUR", name, volume, ((float)price / 100));
    }
}
