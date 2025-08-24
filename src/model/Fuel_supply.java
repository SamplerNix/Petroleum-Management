package model;

import java.sql.Timestamp;

public class Fuel_supply {
    private int supplyid;
    private int stationid;
    private int fueltypeid;
    private double quantity_liters;
    private String suppliername;
    private Timestamp deliverydate;
    //this is where getter is started

    public int getSupplyid() {
        return supplyid;
    }

    public int getStationid() {
        return stationid;
    }

    public int getFueltypeid() {
        return fueltypeid;
    }

    public double getQuantity_liters() {
        return quantity_liters;
    }

    public String getSuppliername() {
        return suppliername;
    }

    public Timestamp getDeliverydate() {
        return deliverydate;
    }
    // this is where setter is started

    public void setSupplyid(int supplyid) {
        this.supplyid = supplyid;
    }

    public void setStationid(int stationid) {
        this.stationid = stationid;
    }

    public void setFueltypeid(int fueltypeid) {
        this.fueltypeid = fueltypeid;
    }

    public void setQuantity_liters(double quantity_liters) {
        this.quantity_liters = quantity_liters;
    }

    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }

    public void setDeliverydate(Timestamp deliverydate) {
        this.deliverydate = deliverydate;
    }
}
