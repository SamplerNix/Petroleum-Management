package model;

public class Fuel_station {
    private int stationid;
    private String stattion_name;
    private String location;
    private int managerid;
    private String contactinfo;
    // this is getter

    public int getStationid() {
        return stationid;
    }

    public String getStattion_name() {
        return stattion_name;
    }

    public String getLocation() {
        return location;
    }

    public int getManagerid() {
        return managerid;
    }

    public String getContactinfo() {
        return contactinfo;
    }
    // this is setter

    public void setStationid(int stationid) {
        this.stationid = stationid;
    }

    public void setStattion_name(String stattion_name) {
        this.stattion_name = stattion_name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setManagerid(int managerid) {
        this.managerid = managerid;
    }

    public void setContactinfo(String contactinfo) {
        this.contactinfo = contactinfo;
    }
}
