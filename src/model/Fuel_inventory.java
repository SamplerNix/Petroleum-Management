package model;

import java.sql.Timestamp;

public class Fuel_inventory {
    private int inventoryid;
    private int stationid;
    private int fueltypeid;
    private double quantityliters;
    private Timestamp lastupdate;
    //this is where getter is started

    public int getInventoryid() {
        return inventoryid;
    }

    public int getStationid() {
        return stationid;
    }

    public int getFueltypeid() {
        return fueltypeid;
    }

    public double getQuantityliters() {
        return quantityliters;
    }

    public Timestamp getLastupdate() {
        return lastupdate;
    }
    // this is where setter is started

    public void setInventoryid(int inventoryid) {
        this.inventoryid = inventoryid;
    }

    public void setStationid(int stationid) {
        this.stationid = stationid;
    }

    public void setFueltypeid(int fueltypeid) {
        this.fueltypeid = fueltypeid;
    }

    public void setQuantityliters(double quantityliters) {
        this.quantityliters = quantityliters;
    }

    public void setLastupdate(Timestamp lastupdate) {
        this.lastupdate = lastupdate;
    }
}
