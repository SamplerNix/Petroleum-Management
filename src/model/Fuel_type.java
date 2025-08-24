package model;

public class Fuel_type {
private int fuel_type_id;
private String fuel_name;
private double price_per_liter;
// This is where getter is started
    public int getFuel_type_id() {
        return fuel_type_id;
    }

    public String getFuel_name() {
        return fuel_name;
    }

    public double getPrice_per_liter() {
        return price_per_liter;
    }
    // this is Where setter is started

    public void setFuel_type_id(int fuel_type_id) {
        this.fuel_type_id = fuel_type_id;
    }

    public void setFuel_name(String fuel_name) {
        this.fuel_name = fuel_name;
    }

    public void setPrice_per_liter(double price_per_liter) {
        this.price_per_liter = price_per_liter;
    }
}
