package model;

public class sales {
    private int salesid;
    private int stationid;
    private int fueltypeid;
    private double quantityliters;
    private double totalprice;
    private String Soldby;
    // this is where getter is started


    public int getSalesid() {
        return salesid;
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

    public double getTotalprice() {
        return totalprice;
    }

    public String getSoldby() {
        return Soldby;
    }
    // this is where setter is started

    public void setSalesid(int salesid) {
        this.salesid = salesid;
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

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public void setSoldby(String soldby) {
        Soldby = soldby;
    }
}
