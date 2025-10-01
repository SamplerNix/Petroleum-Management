package componet.model;
public class FuelType {
    private int fuelTypeId;
    private String fuelName;
    private double pricePerLiter;

    // Constructors
    public FuelType() {}
    
    public FuelType(int fuelTypeId, String fuelName, double pricePerLiter) {
        this.fuelTypeId = fuelTypeId;
        this.fuelName = fuelName;
        this.pricePerLiter = pricePerLiter;
    }

    // Getters and Setters
    public int getFuelTypeId() { return fuelTypeId; }
    public void setFuelTypeId(int fuelTypeId) { this.fuelTypeId = fuelTypeId; }

    public String getFuelName() { return fuelName; }
    public void setFuelName(String fuelName) { this.fuelName = fuelName; }

    public double getPricePerLiter() { return pricePerLiter; }
    public void setPricePerLiter(double pricePerLiter) { this.pricePerLiter = pricePerLiter; }
}
